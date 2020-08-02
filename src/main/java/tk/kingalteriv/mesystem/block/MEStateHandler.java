package tk.kingalteriv.mesystem.block;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.block.Block;

public class MEStateHandler {

    private final Map<Block, BlockMETerminal> cores = new HashMap<>();

    public void registerCore(BlockMETerminal core) {
        this.cores.put(core.getBlock(), core);
    }

    public BlockMETerminal getCore(Block block) {
        return cores.get(block);
    }

    public void unregisterCore(BlockMETerminal core) {
        this.cores.remove(core.getBlock());
    }

    public BlockMETerminal unregisterCore(Block block) {
        return cores.remove(block);
    }

    public void clearCores() {
        this.cores.clear();
    }

}
