package android.content.res;

import android.util.SparseArray;

public class TestTypedArray extends TypedArray {
    SparseArray<String> values = new SparseArray<String>();

    @Override
    public String getString(int index) {
        return values.get(index);
    }

    public void putString(int index, String value) {
        values.put(index, value);
    }
}
