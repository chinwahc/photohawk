/*******************************************************************************
 * Copyright 2010-2014 Vienna University of Technology
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

package at.ac.tuwien.photohawk.commandline;

import at.ac.tuwien.photohawk.commandline.result.HRFloatResultPrinter;
import at.ac.tuwien.photohawk.commandline.result.ResultPrinter;
import at.ac.tuwien.photohawk.commandline.util.ImageReader;
import at.ac.tuwien.photohawk.evaluation.colorconverter.StaticColor;
import at.ac.tuwien.photohawk.evaluation.operation.TransientOperation;
import at.ac.tuwien.photohawk.evaluation.preprocessing.PreprocessingException;
import at.ac.tuwien.photohawk.evaluation.qa.AeQa;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import net.sourceforge.argparse4j.inf.Subparsers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Absolute error command.
 */
class Ae implements Command {

    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final String BASE_KEY = "dcraw.ae";

    private Namespace n = null;

    private Subparsers subparsers;

    private Subparser subparser;

    private AeQa aeQa;

    private ResultPrinter<Float, StaticColor> resultPrinter;

    private ImageReader ir;

    /**
     * Creats a new AE command.
     *
     * @param subparsers the subparser to use
     */
    public Ae(Subparsers subparsers) {
        this.subparsers = subparsers;
    }

    @Override
    public void init() {
        subparser = subparsers.addParser("ae").help("Absolute error metric")
                .setDefault("command", this);

        subparser.addArgument(LEFT).type(Arguments.fileType().verifyCanRead())
                .help("Left file for comparison");
        subparser.addArgument(RIGHT).type(Arguments.fileType().verifyCanRead())
                .help("Right file for comparison");
    }

    @Override
    public void configure(Namespace n) {
        this.n = n;
        resultPrinter = new HRFloatResultPrinter(System.out);
        ir = new ImageReader(BASE_KEY);
        aeQa = new AeQa();
    }

    @Override
    public void evaluate() {
        File left = n.get(LEFT);
        File right = n.get(RIGHT);

        try {
            BufferedImage leftImg = ir.readImage(left, n.getString(Photohawk.READ_LEFT_KEY), n.getString(Photohawk.READ_RIGHT_KEY));
            BufferedImage rightImg = ir.readImage(right, n.getString(Photohawk.READ_RIGHT_KEY), n.getString(Photohawk.READ_LEFT_KEY));

            // Evaluate
            TransientOperation<Float, StaticColor> op = aeQa.evaluate(leftImg, rightImg);

            resultPrinter.print(op);
        } catch (PreprocessingException e) {
            subparser.printUsage();
            System.err.println("Cannot process files");
            System.err.println(e.getMessage());
        } catch (IOException e) {
            subparser.printUsage();
            System.err.println("Cannot read file");
            System.err.println(e.getMessage());
        }
    }
}