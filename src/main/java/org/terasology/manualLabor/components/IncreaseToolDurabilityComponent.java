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
package org.terasology.manualLabor.components;

import org.terasology.entitySystem.Component;

/**
 * Add this to a substance so that when a tool is created, its base durability is increased based on how much of the substance is present
 */
public class IncreaseToolDurabilityComponent implements Component, ToolModificationDescription {
    public float increasePerSubstanceAmount;

    @Override
    public String getDescription() {
        String upDown = increasePerSubstanceAmount < 0f ? "Decreases" : "Increases";
        return String.format(upDown + " tool durability by %.1f for every unit of material", Math.abs(increasePerSubstanceAmount));
    }
}
