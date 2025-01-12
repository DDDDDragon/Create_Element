package org.mantodea.create_element.Items.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mantodea.create_element.Interfaces.IModItem;

public class MetalDetector extends Item implements IModItem
{
    public MetalDetector()
    {
        super(new Properties().durability(100));
    }

    @Override
    public String GetID()
    {
        return "metal_detector";
    }

    @Override
    @NotNull
    public InteractionResult useOn(UseOnContext context)
    {
        if(!context.getLevel().isClientSide())
        {
            BlockPos position = context.getClickedPos();
            Player player = context.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i <= position.getY() + 64; i++)
            {
                BlockState state = context.getLevel().getBlockState(position.below(i));

                if(IsValuableBlock(state))
                {
                    OutputBlockCoordinates(position, player, state.getBlock());
                    foundBlock = true;
                    break;
                }
            }

            if(!foundBlock)
                player.sendSystemMessage(Component.literal("No valuable things."));

            context.getItemInHand().hurtAndBreak(1, context.getPlayer(),
                    p -> p.broadcastBreakEvent(p.getUsedItemHand()));
        }

        return InteractionResult.SUCCESS;
    }

    private void OutputBlockCoordinates(BlockPos pos, Player player, Block block)
    {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " +
                pos.toString()));
    }

    public boolean IsValuableBlock(BlockState state)
    {
        return state.is(Blocks.DIAMOND_ORE);
    }
}
