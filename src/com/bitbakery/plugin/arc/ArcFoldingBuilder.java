package com.bitbakery.plugin.arc;

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

import static com.bitbakery.plugin.arc.lexer.ArcTokenTypes.*;
import com.bitbakery.plugin.arc.psi.ArcElementTypes;
import static com.bitbakery.plugin.arc.psi.ArcElementTypes.*;
import com.bitbakery.plugin.arc.psi.Def;
import com.bitbakery.plugin.arc.psi.Mac;
import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines how code folding should behave for Arc files
 */
public class ArcFoldingBuilder implements FoldingBuilder {

    public String getPlaceholderText(ASTNode node) {
        if (node.getElementType() == FUNCTION_DEFINITION) {
            Def def = (Def) node.getPsi();
            return "(def " + def.getName() + " ...)";
        } else if (node.getElementType() == MACRO_DEFINITION) {
            Mac def = (Mac) node.getPsi();
            return "(mac " + def.getName() + " ...)";
        } else if (node.getElementType() == SINGLE_ARG_ANONYMOUS_FUNCTION_DEFINITION) {
            return "[...]";
        } else if (node.getElementType() == ANONYMOUS_FUNCTION_DEFINITION) {
            return "(fn ...)";
        } else if (node.getElementType() == BLOCK_COMMENT || node.getElementType() == MULTILINE_COMMENT) {
            String text = node.getText();
            return text.length() > 20 ? text.substring(0, 20) + "..." : text;
        }
        return null;
    }

    public boolean isCollapsedByDefault(ASTNode node) {        
        return false;
    }

    public FoldingDescriptor[] buildFoldRegions(ASTNode node, Document document) {
        touchTree(node);
        List<FoldingDescriptor> descriptors = new ArrayList<FoldingDescriptor>();
        appendDescriptors(node, descriptors);
        return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
    }

    /**
     * We have to touch the PSI tree to get the folding to show up when we first open a file
     */
    private void touchTree(ASTNode node) {
        if (node.getElementType() == ArcElementTypes.FILE) {
            final PsiElement firstChild = node.getPsi().getFirstChild();
        }
    }

    private void appendDescriptors(final ASTNode node, final List<FoldingDescriptor> descriptors) {
        if (isFoldableNode(node)) {
            descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
        }

        ASTNode child = node.getFirstChildNode();
        while (child != null) {
            appendDescriptors(child, descriptors);
            child = child.getTreeNext();
        }
    }

    private boolean isFoldableNode(ASTNode node) {
        return (node.getElementType() == FUNCTION_DEFINITION)
                || (node.getElementType() == MACRO_DEFINITION)
                || (node.getElementType() == SINGLE_ARG_ANONYMOUS_FUNCTION_DEFINITION)
                || (node.getElementType() == ANONYMOUS_FUNCTION_DEFINITION)
                || (node.getElementType() == MULTILINE_COMMENT)
                || (node.getElementType() == BLOCK_COMMENT);
    }
}
