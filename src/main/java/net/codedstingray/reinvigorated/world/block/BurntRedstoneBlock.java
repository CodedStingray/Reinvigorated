package net.codedstingray.reinvigorated.world.block;

import net.codedstingray.reinvigorated.world.block.state.properties.ReinvigoratedBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Orientation;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class BurntRedstoneBlock extends Block {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty EMITTING = ReinvigoratedBlockStateProperties.EMITTING;

    public BurntRedstoneBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(POWERED, false)
                .setValue(EMITTING, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED, EMITTING);
    }

    @Override
    public BlockState getStateForPlacement(final BlockPlaceContext context) {
        BlockState state = this.defaultBlockState();

        if (context.getLevel().hasNeighborSignal(context.getClickedPos())) {
            state = state.setValue(EMITTING, true).setValue(POWERED, true);
        }

        return state;
    }

    @Override
    protected void neighborChanged(
            BlockState state,
            final Level level,
            final BlockPos pos,
            final Block block,
            @Nullable final Orientation orientation,
            final boolean movedByPiston
    ) {
        if (!level.isClientSide()) {
            boolean signal = level.hasNeighborSignal(pos);
            if (signal != state.getValue(POWERED)) {
                if (state.getValue(EMITTING) != signal) {
                    state = state.setValue(EMITTING, signal);
                }

                level.setBlockAndUpdate(pos, state.setValue(POWERED, signal));
            }
        }
    }

    @Override
    public void animateTick(final BlockState state, final Level level, final BlockPos pos, final RandomSource random) {
        if (state.getValue(EMITTING)) {
            spawnParticles(level, pos);
        }
    }

    private static void spawnParticles(final Level level, final BlockPos pos) {
        double offset = 0.5625;
        RandomSource random = level.getRandom();

        for (Direction direction : Direction.values()) {
            BlockPos relative = pos.relative(direction);
            if (!level.getBlockState(relative).isSolidRender()) {
                Direction.Axis axis = direction.getAxis();
                double dx = axis == Direction.Axis.X ? 0.5 + offset * direction.getStepX() : random.nextFloat();
                double dy = axis == Direction.Axis.Y ? 0.5 + offset * direction.getStepY() : random.nextFloat();
                double dz = axis == Direction.Axis.Z ? 0.5 + offset * direction.getStepZ() : random.nextFloat();
                level.addParticle(DustParticleOptions.REDSTONE, pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz, 0.0, 0.0, 0.0);
            }
        }
    }
}
