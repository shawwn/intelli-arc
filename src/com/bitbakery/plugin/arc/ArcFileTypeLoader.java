package com.bitbakery.plugin.arc;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;

/*
 * Copyright (c) Kurt Christensen, 2009
 *
 *  Licensed under the Artistic License, Version 2.0 (the "License"); you may not use this
 *  file except in compliance with the License. You may obtain a copy of the License at:
 *
 *  http://www.opensource.org/licenses/artistic-license-2.0.php
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under
 *  the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 *  OF ANY KIND, either express or implied. See the License for the specific language
 *  governing permissions and limitations under the License..
 */

/**
 * Created by IntelliJ IDEA.
 * User: kurtc
 * Date: Mar 2, 2009
 * Time: 9:59:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class ArcFileTypeLoader extends FileTypeFactory {
    public static final ArcFileType ARC = new ArcFileType();

    public void createFileTypes(@NotNull FileTypeConsumer consumer) {
        consumer.consume(ARC, ArcFileType.EXTENSION);
    }
}
