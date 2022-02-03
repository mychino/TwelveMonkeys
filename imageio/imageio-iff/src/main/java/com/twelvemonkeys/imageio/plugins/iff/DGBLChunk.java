/*
 * Copyright (c) 2022, Harald Kuhr
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * * Neither the name of the copyright holder nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.twelvemonkeys.imageio.plugins.iff;

import javax.imageio.IIOException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * DGBLChunk
 *
 * @author <a href="mailto:harald.kuhr@gmail.com">Harald Kuhr</a>
 * @version $Id: DGBLChunk.java,v 1.0 28.feb.2006 02:10:07 haku Exp$
 */
final class DGBLChunk extends IFFChunk {

    /*
    //
    struct DGBL = {
//
// Size of source display
//
      UWORD  DisplayWidth,DisplayHeight;
//
// Type of compression
//
      UWORD  Compression;
//
// Pixel aspect, a ration w:h
//
      UBYTE  xAspect,yAspect;
    };

     */
    int displayWidth;
    int displayHeight;
    int compressionType;
    int xAspect;
    int yAspect;

    DGBLChunk(int chunkLength) {
        super(IFF.CHUNK_DGBL, chunkLength);
    }

    @Override
    void readChunk(final DataInput input) throws IOException {
        if (chunkLength != 8) {
            throw new IIOException("Unknown DBGL chunk length: " + chunkLength);
        }

        displayWidth = input.readUnsignedShort();
        displayHeight = input.readUnsignedShort();
        compressionType = input.readUnsignedShort();
        xAspect = input.readUnsignedByte();
        yAspect = input.readUnsignedByte();
    }

    @Override
    void writeChunk(final DataOutput output) {
        throw new InternalError("Not implemented: writeChunk()");
    }

    @Override
    public String toString() {
        return super.toString() +
                "{displayWidth=" + displayWidth +
                ", displayHeight=" + displayHeight +
                ", compression=" + compressionType +
                ", xAspect=" + xAspect +
                ", yAspect=" + yAspect +
                '}';
    }
}
