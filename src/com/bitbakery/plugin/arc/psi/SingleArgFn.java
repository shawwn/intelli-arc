package com.bitbakery.plugin.arc.psi;

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

import com.bitbakery.plugin.arc.ArcIcons;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * PSI element for ingle-arg anonymous Arc function definitions (e.g., [+ 1 _]).
 */
public class SingleArgFn extends Expression implements PsiNamedElement {
    public SingleArgFn(ASTNode node) {
        super(node);
    }

    public String getName() {
        return "anonymous";
    }

    public String getText() {
        return super.getText();
    }

    public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException {
        // TODO - We can't name an anonymous function!!
        return this;
    }

    public Icon getIcon(int flags) {
        return ArcIcons.ARC_DEF_ICON; // TODO - Need an anonymous def icon
    }
}