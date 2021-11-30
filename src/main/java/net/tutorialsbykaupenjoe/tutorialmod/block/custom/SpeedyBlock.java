package net.tutorialsbykaupenjoe.tutorialmod.block.custom;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.tutorialsbykaupenjoe.tutorialmod.world.dimension.KJTeleporter;
import net.tutorialsbykaupenjoe.tutorialmod.world.dimension.ModDimensions;

public class SpeedyBlock extends Block {
    public SpeedyBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            if (!pPlayer.isCrouching()) {
                MinecraftServer server = pLevel.getServer();

                if (server != null) {
                    if (pLevel.dimension() == ModDimensions.KJDim) {
                        ServerLevel overWorld = server.getLevel(Level.OVERWORLD);
                        if (overWorld != null) {
                            pPlayer.changeDimension(overWorld, new KJTeleporter(pPos, false));
                        }
                    } else {
                        ServerLevel kjDim = server.getLevel(ModDimensions.KJDim);
                        if (kjDim != null) {
                            pPlayer.changeDimension(kjDim, new KJTeleporter(pPos, true));
                        }
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if(!pLevel.isClientSide()) {
            if(pEntity instanceof LivingEntity) {
                LivingEntity entity = ((LivingEntity) pEntity);
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200));
            }
        }

        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}
