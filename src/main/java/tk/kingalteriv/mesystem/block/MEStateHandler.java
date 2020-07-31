package tk.kingalteriv.mesystem.block;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.block.Block;

public class MEStateHandler {

    private final Map<Block, BlockMECore> cores = new HashMap<>();

    public void registerCore(BlockMECore core) {
        this.cores.put(core.getBlock(), core);
    }

    public BlockMECore getCore(Block block) {
        return cores.get(block);
    }

    public void unregisterCore(BlockMECore core) {
        this.cores.remove(core.getBlock());
    }

    public BlockMECore unregisterCore(Block block) {
        return cores.remove(block);
    }

    public void clearCores() {
        this.cores.clear();
    }

}
