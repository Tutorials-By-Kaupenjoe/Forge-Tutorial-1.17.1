package net.tutorialsbykaupenjoe.tutorialmod.item.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.Util;
import net.minecraft.client.ParticleStatus;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.tutorialsbykaupenjoe.tutorialmod.block.ModBlocks;
import net.tutorialsbykaupenjoe.tutorialmod.item.ModItems;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class SmartBlowtorchItem extends Item {
    private static final Map<Block, Item> BLOW_TORCH_ITEM_CRAFT =
            new ImmutableMap.Builder<Block, Item>()
                    .put(ModBlocks.TITANIUM_BLOCK.get(), ModItems.TITANIUM_NUGGET.get())
                    .put(Blocks.SAND, Blocks.GLASS.asItem())
                    .build();

    public SmartBlowtorchItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()) {
            Level level = pContext.getLevel();
            BlockPos positionClicked = pContext.getClickedPos();
            Block blockClicked = level.getBlockState(positionClicked).getBlock();
            ItemStack stack = pContext.getItemInHand();

            if(blockClicked == Blocks.MAGMA_BLOCK) {
                if(pContext.getHand() == InteractionHand.MAIN_HAND) {
                    CompoundTag tag = new CompoundTag();

                    if(stack.hasTag()) {
                        increaseCharge(stack);
                    } else {
                        tag.putInt("charge", 1);
                        stack.setTag(tag);
                    }

                    return InteractionResult.SUCCESS;
                }
            } else {
                if(stack.hasTag() && stack.getTag().getInt("charge") > 0) {
                    if(canBlowTorch(blockClicked)) {
                        ItemEntity entityItem = new ItemEntity(level,
                                positionClicked.getX(), positionClicked.getY(), positionClicked.getZ(),
                                new ItemStack(BLOW_TORCH_ITEM_CRAFT.get(blockClicked), 1));

                        level.destroyBlock(positionClicked, false);
                        level.addFreshEntity(entityItem);

                        decreaseCharge(stack);

                        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), p -> {
                            p.broadcastBreakEvent(pContext.getHand());
                        });
                    } else {
                        pContext.getPlayer().sendMessage(new TextComponent("Cannot blow torch this Block!"),
                                Util.NIL_UUID);
                    }
                } else {
                    pContext.getPlayer().sendMessage(new TextComponent("Blow torch doesn't have charge left!"),
                            Util.NIL_UUID);
                }
            }
        }

        return InteractionResult.SUCCESS;
    }

    private void increaseCharge(ItemStack stack) {
        stack.getTag().putInt("charge", stack.getTag().getInt("charge") + 1);
    }

    private void decreaseCharge(ItemStack stack) {
        stack.getTag().putInt("charge", stack.getTag().getInt("charge") - 1);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel,
                                List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(new TranslatableComponent("tooltip.tutorialmod.blowtorch"));
            if(pStack.hasTag()) {
                pTooltipComponents.add(new TextComponent("Charge: " + pStack.getTag().getInt("charge")));
            }
        } else {
            pTooltipComponents.add(new TranslatableComponent("tooltip.tutorialmod.blowtorch_shift"));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private boolean canBlowTorch(Block block) {
        return BLOW_TORCH_ITEM_CRAFT.containsKey(block);
    }
}
