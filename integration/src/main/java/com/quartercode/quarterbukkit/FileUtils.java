/*
 * This file is part of QuarterBukkit-Integration.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * QuarterBukkit-Integration is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * QuarterBukkit-Integration is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with QuarterBukkit-Integration. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.quarterbukkit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * This class provides some simple utility methods for advanced file operations.
 */
public class FileUtils {

    /**
     * Copies the given source {@link File} or directory to the given destination {@link File} or directory.
     * If the source is a directory, this will copy it recursively.
     * 
     * @param source The source {@link File} or directory to copy to the given destination {@link File} or directory.
     * @param destination The destination {@link File} or directory where to copy the given source.
     * @throws IOException Something goes wrong while executing some file operations.
     */
    public static void copy(File source, File destination) throws IOException {

        if (source.isDirectory()) {
            destination.mkdirs();

            for (File entry : source.listFiles()) {
                copy(new File(source, entry.getName()), new File(destination, entry.getName()));
            }
        } else {

            InputStream inputStream = new FileInputStream(source);
            OutputStream outputStream = new FileOutputStream(destination);

            byte[] buffer = new byte[0xFFFF];
            int numberOfBytes;
            while ( (numberOfBytes = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, numberOfBytes);
            }

            inputStream.close();
            outputStream.close();
        }
    }

    /**
     * Deletes the given {@link File} or directory and doesn't care if the directory is empty.
     * 
     * @param file The {@link File} or directory to delete completely.
     * @throws IOException Something goes wrong while deleting a {@link File}.
     */
    public static void delete(File file) throws IOException {

        if (file.isDirectory()) {
            for (File entry : file.listFiles()) {
                delete(entry);
            }
        }

        if (!file.delete()) {
            throw new IOException("Can't delete file '" + file.getAbsolutePath() + "'");
        }
    }

    /**
     * Unzips the given zip {@link File} into a new directory called destination.
     * 
     * @param zip The zip {@link File} to unzip.
     * @param destination The directory where to put the contents of the zip {@link File}. It may be created if it doesn't exist.
     * @throws IOException Something goes wrong while executing some file operations.
     */
    public static void unzip(File zip, File destination) throws IOException {

        ZipFile zipFile = new ZipFile(zip);

        for (ZipEntry zipEntry : Collections.list(zipFile.entries())) {
            File file = new File(destination, zipEntry.getName());

            if (zipEntry.isDirectory()) {
                file.mkdirs();
            } else {
                new File(file.getParent()).mkdirs();

                InputStream inputStream = zipFile.getInputStream(zipEntry);
                OutputStream outputStream = new FileOutputStream(file);

                byte[] buffer = new byte[0xFFFF];
                for (int lenght; (lenght = inputStream.read(buffer)) != -1;) {
                    outputStream.write(buffer, 0, lenght);
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        }

        zipFile.close();
    }

    private FileUtils() {

    }

}