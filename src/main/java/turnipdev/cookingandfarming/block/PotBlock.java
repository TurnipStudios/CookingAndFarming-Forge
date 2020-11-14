package turnipdev.cookingandfarming.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class PotBlock extends Block {
    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    // The messy VoxelShapes for different directions
    private static final VoxelShape SHAPE_NORTH = Stream.of(
            Block.makeCuboidShape(2, 0, 3, 3, 6, 13),
            Block.makeCuboidShape(3, 0, 13, 13, 6, 14),
            Block.makeCuboidShape(13, 0, 3, 14, 6, 13),
            Block.makeCuboidShape(3, 0, 2, 13, 6, 3),
            Block.makeCuboidShape(3, 0, 3, 13, 1, 13),
            Block.makeCuboidShape(1, 3, 6, 2, 4, 7),
            Block.makeCuboidShape(1, 3, 9, 2, 4, 10),
            Block.makeCuboidShape(14, 3, 6, 15, 4, 7),
            Block.makeCuboidShape(0, 3, 7, 1, 4, 9),
            Block.makeCuboidShape(14, 3, 9, 15, 4, 10),
            Block.makeCuboidShape(15, 3, 7, 16, 4, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_EAST = Stream.of(
            Block.makeCuboidShape(3, 0, 2, 13, 6, 3),
            Block.makeCuboidShape(2, 0, 3, 3, 6, 13),
            Block.makeCuboidShape(3, 0, 13, 13, 6, 14),
            Block.makeCuboidShape(13, 0, 3, 14, 6, 13),
            Block.makeCuboidShape(3, 0, 3, 13, 1, 13),
            Block.makeCuboidShape(9, 3, 1, 10, 4, 2),
            Block.makeCuboidShape(6, 3, 1, 7, 4, 2),
            Block.makeCuboidShape(9, 3, 14, 10, 4, 15),
            Block.makeCuboidShape(7, 3, 0, 9, 4, 1),
            Block.makeCuboidShape(6, 3, 14, 7, 4, 15),
            Block.makeCuboidShape(7, 3, 15, 9, 4, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_SOUTH = Stream.of(
            Block.makeCuboidShape(13, 0, 3, 14, 6, 13),
            Block.makeCuboidShape(3, 0, 2, 13, 6, 3),
            Block.makeCuboidShape(2, 0, 3, 3, 6, 13),
            Block.makeCuboidShape(3, 0, 13, 13, 6, 14),
            Block.makeCuboidShape(3, 0, 3, 13, 1, 13),
            Block.makeCuboidShape(14, 3, 9, 15, 4, 10),
            Block.makeCuboidShape(14, 3, 6, 15, 4, 7),
            Block.makeCuboidShape(1, 3, 9, 2, 4, 10),
            Block.makeCuboidShape(15, 3, 7, 16, 4, 9),
            Block.makeCuboidShape(1, 3, 6, 2, 4, 7),
            Block.makeCuboidShape(0, 3, 7, 1, 4, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_WEST = Stream.of(
            Block.makeCuboidShape(3, 0, 13, 13, 6, 14),
            Block.makeCuboidShape(13, 0, 3, 14, 6, 13),
            Block.makeCuboidShape(3, 0, 2, 13, 6, 3),
            Block.makeCuboidShape(2, 0, 3, 3, 6, 13),
            Block.makeCuboidShape(3, 0, 3, 13, 1, 13),
            Block.makeCuboidShape(6, 3, 14, 7, 4, 15),
            Block.makeCuboidShape(9, 3, 14, 10, 4, 15),
            Block.makeCuboidShape(6, 3, 1, 7, 4, 2),
            Block.makeCuboidShape(7, 3, 15, 9, 4, 16),
            Block.makeCuboidShape(9, 3, 1, 10, 4, 2),
            Block.makeCuboidShape(7, 3, 0, 9, 4, 1)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    public PotBlock(Properties properties) {
        super(properties);
    }

    // Rotate the VoxelShape depending on the direction the block is facing
    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case EAST:
                return SHAPE_EAST;
            case SOUTH:
                return SHAPE_SOUTH;
            case WEST:
                return SHAPE_WEST;
            case NORTH:
            default:
                return SHAPE_NORTH;
        }
    }

    // Rotate the block depending on the player's direction
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
