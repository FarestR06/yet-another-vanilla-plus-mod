package com.farestr06.yavpm.block.custom.entity;

import com.farestr06.yavpm.block.YavpmBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ContainerLock;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class KeylockBlockEntity extends BlockEntity {
    private ContainerLock lock = ContainerLock.EMPTY;

    public KeylockBlockEntity(BlockPos pos, BlockState state) {
        super(YavpmBlockEntities.KEYLOCK, pos, state);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        this.lock = ContainerLock.fromNbt(nbt);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        this.lock.writeNbt(nbt);
    }

    public ContainerLock getLock() {
        return lock;
    }

    public void setLock(ContainerLock lock) {
        this.lock = lock;
    }

    public boolean checkUnlocked(PlayerEntity player) {
        return LockableContainerBlockEntity.checkUnlocked(player, this.lock, Text.translatable(YavpmBlocks.KEYLOCK.getTranslationKey()));
    }

    @Override
    protected void readComponents(BlockEntity.ComponentsAccess components) {
        super.readComponents(components);
        this.lock = components.getOrDefault(DataComponentTypes.LOCK, ContainerLock.EMPTY);
    }

    @Override
    protected void addComponents(ComponentMap.Builder componentMapBuilder) {
        super.addComponents(componentMapBuilder);
        if (!this.lock.equals(ContainerLock.EMPTY)) {
            componentMapBuilder.add(DataComponentTypes.LOCK, this.lock);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void removeFromCopiedStackNbt(NbtCompound nbt) {
        nbt.remove("Lock");
    }
}
