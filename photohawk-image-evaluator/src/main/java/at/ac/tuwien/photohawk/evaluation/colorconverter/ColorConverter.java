/*******************************************************************************
 * Copyright 2010-2013 Vienna University of Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package at.ac.tuwien.photohawk.evaluation.colorconverter;

/**
 * This interface describes a ColorConverter that can transform color values
 * from one into another color system.
 *
 * @param <T> Color type of this color converter
 * @author Stephan Bauer (stephan.bauer@student.tuwien.ac.at)
 */
public interface ColorConverter<T extends StaticColor> extends ColorSystem {

    /**
     * Returns the color channels at the specified coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the color channel
     */
    T getColorChannels(int x, int y);

    /**
     * Returns the null color.
     *
     * @return the null color
     */
    StaticColor getNullColor();
}
