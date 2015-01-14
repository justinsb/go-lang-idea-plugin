/*
 * Copyright 2013-2014 Sergey Ignatov, Alexander Zolotov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.goide.inspections;

import com.goide.psi.*;
import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GoDuplicateArgumentInspection extends GoInspectionBase {
  @NotNull
  @Override
  protected GoVisitor buildGoVisitor(@NotNull final ProblemsHolder holder,
                                     @SuppressWarnings({"UnusedParameters", "For future"}) @NotNull LocalInspectionToolSession session) {
    return new GoVisitor() {
      @Override
      public void visitCompositeElement(@NotNull GoCompositeElement o) {
        if (o instanceof GoSignatureOwner) {
          check(((GoSignatureOwner)o).getSignature(), holder);
        }
      }
    };
  }

  public void check(@Nullable GoSignature o, @NotNull ProblemsHolder holder) {
    if (o == null) return;
    List<GoParameterDeclaration> params = o.getParameters().getParameterDeclarationList();
    Set<String> parameters = new LinkedHashSet<String>();
    for (GoParameterDeclaration fp : params) {
      for (GoParamDefinition parameter : fp.getParamDefinitionList()) {
        if (parameter.isBlank()) continue;
        String name = parameter.getName();
        if (name != null && parameters.contains(name)) {
          holder.registerProblem(parameter, errorText(name), ProblemHighlightType.GENERIC_ERROR_OR_WARNING);
        }
        else {
          parameters.add(name);
        }
      }
    }
  }

  protected static String errorText(@NotNull String name) {
    return "Duplicate argument " + "'" + name + "'";
  }
}