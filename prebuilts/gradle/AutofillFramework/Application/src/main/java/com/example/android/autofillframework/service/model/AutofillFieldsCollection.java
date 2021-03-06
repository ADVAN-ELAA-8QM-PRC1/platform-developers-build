/*
 * Copyright (C) 2017 The Android Open Source Project
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
package com.example.android.autofillframework.service.model;

import android.view.autofill.AutofillId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public final class AutofillFieldsCollection {

    private final List<AutofillId> mAutofillIds = new ArrayList<>();
    private final HashMap<String, List<AutofillField>> mAutofillHintsToFieldsMap = new HashMap<>();
    private final List<String> mAllAutofillHints = new ArrayList<>();
    private final List<String> mFocusedAutofillHints = new ArrayList<>();
    private int size = 0;
    private int mSaveType = 0;

    public void add(AutofillField autofillField) {
        mSaveType |= autofillField.getSaveType();
        size++;
        mAutofillIds.add(autofillField.getId());
        List<String> hintsList = Arrays.asList(autofillField.getHints());
        mAllAutofillHints.addAll(hintsList);
        if (autofillField.isFocused()) {
            mFocusedAutofillHints.addAll(hintsList);
        }
        for (String hint : autofillField.getHints()) {
            if (mAutofillHintsToFieldsMap.get(hint) == null) {
                mAutofillHintsToFieldsMap.put(hint, new ArrayList<AutofillField>());
            }
            mAutofillHintsToFieldsMap.get(hint).add(autofillField);
        }
    }

    public int getSaveType() {
        return mSaveType;
    }

    public AutofillId[] getAutofillIds() {
        return mAutofillIds.toArray(new AutofillId[size]);
    }

    public List<AutofillField> getFieldsForHint(String hint) {
        return mAutofillHintsToFieldsMap.get(hint);
    }

    public List<String> getFocusedHints() {
        return mFocusedAutofillHints;
    }

    public List<String> getAllHints() {
        return mAllAutofillHints;
    }
}
