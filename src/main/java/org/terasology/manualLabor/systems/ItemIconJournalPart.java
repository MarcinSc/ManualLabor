/*
 * Copyright 2014 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.manualLabor.systems;

import org.terasology.entitySystem.entity.EntityManager;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.journal.JournalManager;
import org.terasology.logic.inventory.ItemComponent;
import org.terasology.math.Rect2i;
import org.terasology.math.Vector2i;
import org.terasology.registry.CoreRegistry;
import org.terasology.rendering.assets.texture.TextureRegion;
import org.terasology.rendering.nui.Canvas;
import org.terasology.rendering.nui.HorizontalAlign;
import org.terasology.world.block.family.BlockFamily;

public class ItemIconJournalPart implements JournalManager.JournalEntryPart {
    private String itemUri;
    private BlockFamily blockFamily;
    private TextureRegion cachedTexture;
    private HorizontalAlign horizontalAlign;

    public ItemIconJournalPart(String itemUri, HorizontalAlign horizontalAlign) {
        this.itemUri = itemUri;
        this.horizontalAlign = horizontalAlign;
    }

    public ItemIconJournalPart(BlockFamily blockFamily, HorizontalAlign horizontalAlign) {
        this.blockFamily = blockFamily;
        this.horizontalAlign = horizontalAlign;
    }

    private TextureRegion getItemTexture() {
        if (cachedTexture == null) {
            EntityManager entityManager = CoreRegistry.get(EntityManager.class);
            EntityRef item = entityManager.create(itemUri);
            ItemComponent itemComponent = item.getComponent(ItemComponent.class);
            cachedTexture = itemComponent.icon;
            item.destroy();
        }
        return cachedTexture;
    }

    @Override
    public Vector2i getPreferredSize(long date) {
        return getItemTexture().size();
    }

    @Override
    public void render(Canvas canvas, Rect2i region, long date) {
        int x = horizontalAlign.getOffset(getItemTexture().getWidth(), region.width());
        canvas.drawTexture(getItemTexture(), Rect2i.createFromMinAndMax(region.minX() + x, region.minY(), region.minX() + x + getItemTexture().getWidth(), region.maxY()));
    }
}
