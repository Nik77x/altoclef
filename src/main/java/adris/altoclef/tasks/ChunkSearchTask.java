package adris.altoclef.tasks;

import adris.altoclef.AltoClef;
import adris.altoclef.Debug;
import adris.altoclef.tasksystem.Task;
import adris.altoclef.util.csharpisbetter.ActionListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.chunk.WorldChunk;

import java.util.*;

/**
 * Use to walk through and search interconnected structures or biomes.
 *
 * Example use cases:
 *  - Search a dark forest for a woodland mansion and avoid going to different biomes
 *  - Search a nether fortress for blaze spawners
 *  - Search a stronghold for the portal
 */
public abstract class ChunkSearchTask extends Task {

    private final BlockPos _startPoint;

    // We're either searched or will be searched later.
    private Set<ChunkPos> _consideredAlready = new HashSet<>();
    // We definitely were searched before.
    private Set<ChunkPos> _searchedAlready = new HashSet<>();
    private ArrayList<ChunkPos> _searchLater = new ArrayList<>();

    private ArrayList<ChunkPos> _justLoaded = new ArrayList<>();

    private final Object _searchMutex = new Object();

    public ChunkSearchTask(BlockPos startPoint) {
        _startPoint = startPoint;
    }
    public ChunkSearchTask(ChunkPos chunkPos) {
        _startPoint = chunkPos.getStartPos().add(1, 1, 1);
    }

    public Set<ChunkPos> getSearchedChunks() {
        return _searchedAlready;
    }

    private ActionListener<WorldChunk> chunkLoadEvent = new ActionListener<WorldChunk>() {
        @Override
        public void invoke(WorldChunk value) {
            onChunkLoad(value);
        }
    };

    private boolean _first = true;

    private boolean _finished = false;

    public boolean finished() {return _finished;}

    @Override
    protected void onStart(AltoClef mod) {
        /*
        _consideredAlready.clear();
        _searchLater.clear();
        _searchedAlready.clear();
         */
        if (_first) {
            _finished = false;
            _first = false;
            ChunkPos startPos = mod.getWorld().getChunk(_startPoint).getPos();
            synchronized (_searchMutex) {
                searchChunkOrQueueSearch(mod, startPos);
            }
        }

        mod.getOnChunkLoad().addListener(chunkLoadEvent);
    }

    @Override
    protected Task onTick(AltoClef mod) {

        // Backup in case if chunk search fails?
        onChunkLoad((WorldChunk) mod.getWorld().getChunk(mod.getPlayer().getBlockPos()));

        synchronized (_searchMutex) {
            // Search all items from _justLoaded that we ought to search.
            for (ChunkPos justLoaded : _justLoaded) {
                if (_searchLater.contains(justLoaded)) {
                    // Search this one. If we succeed, we no longer need to search.
                    if (trySearchChunk(mod, justLoaded)) {
                        _searchLater.remove(justLoaded);
                    }
                }
            }
            _justLoaded.clear();
        }

        // Now that we have an updated map, go to the nearest
        ChunkPos closest = getBestChunk(mod, _searchLater);

        if (closest == null) {
            _finished = true;
            Debug.logWarning("Failed to find any chunks to go to. If we finish, that means we scanned all possible chunks.");
            return null;
        }

        return new GetToChunkTask(closest);
    }

    // Virtual
    protected ChunkPos getBestChunk(AltoClef mod, List<ChunkPos> chunks) {
        double lowestScore = Double.POSITIVE_INFINITY;
        ChunkPos bestChunk = null;
        for (ChunkPos toSearch : chunks) {
            double cx = (toSearch.getStartX() + toSearch.getEndX() + 1) / 2.0, cz = (toSearch.getStartZ() + toSearch.getEndZ() + 1) / 2.0;
            double px = mod.getPlayer().getX(), pz = mod.getPlayer().getZ();
            double distanceSq = (cx - px) * (cx - px) + (cz - pz) * (cz - pz);
            double distanceToCenterSq = new Vec3d(_startPoint.getX() - cx, 0, _startPoint.getZ() - cz).lengthSquared();
            double score = distanceSq + distanceToCenterSq*0.8;
            if (score < lowestScore) {
                lowestScore = score;
                bestChunk = toSearch;
            }
        }
        return bestChunk;
    }

    @Override
    protected void onStop(AltoClef mod, Task interruptTask) {
        mod.getOnChunkLoad().removeListener(chunkLoadEvent);
    }

    @Override
    public boolean isFinished(AltoClef mod) {
        return _searchLater.size() == 0;
    }

    @Override
    protected boolean isEqual(Task obj) {
        if (obj instanceof ChunkSearchTask) {
            ChunkSearchTask task = (ChunkSearchTask) obj;
            if (!task._startPoint.equals(_startPoint)) return false;
            return isChunkSearchEqual(task);
        }
        return false;
    }

    private void searchChunkOrQueueSearch(AltoClef mod, ChunkPos pos) {
        // Don't search/consider this chunk again.
        if (_consideredAlready.contains(pos)) {
            return;
        }
        _consideredAlready.add(pos);

        if (!trySearchChunk(mod, pos)) {
            // We'll check it later if we haven't searched it.
            if (!_searchedAlready.contains(pos)) {
                _searchLater.add(pos);
            }
        }
    }

    /**
     * Try to search the chunk.
     * @param pos chunk to search
     * @return true if we're DONE searching this chunk
     *         false if we need to SEARCH IT IN PERSON
     */
    private boolean trySearchChunk(AltoClef mod, ChunkPos pos) {
        // Do NOT search later.
        if (_searchedAlready.contains(pos)) {
            return true;
        }
        if (mod.getChunkTracker().isChunkLoaded(pos)) {
            _searchedAlready.add(pos);
            if (isChunkPartOfSearchSpace(mod, pos)) {
                // This chunk may lead to more, so either search or enqueue its neighbors.
                searchChunkOrQueueSearch(mod, new ChunkPos(pos.x + 1, pos.z));
                searchChunkOrQueueSearch(mod, new ChunkPos(pos.x - 1, pos.z));
                searchChunkOrQueueSearch(mod, new ChunkPos(pos.x, pos.z + 1));
                searchChunkOrQueueSearch(mod, new ChunkPos(pos.x, pos.z - 1));
            }
            return true;
        }
        return false;
    }

    private void onChunkLoad(WorldChunk chunk) {
        if (chunk == null) return;
        synchronized (_searchMutex) {
            if (!_searchedAlready.contains(chunk.getPos())) {
                _justLoaded.add(chunk.getPos());
            }
        }
    }

    protected abstract boolean isChunkPartOfSearchSpace(AltoClef mod, ChunkPos pos);
    protected abstract boolean isChunkSearchEqual(ChunkSearchTask other);
}
