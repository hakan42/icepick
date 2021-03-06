package com.github.frankiesardo.icepick.annotation;

import java.io.Writer;

class IcicleFragmentActivityWriter extends IcicleWriter {

    IcicleFragmentActivityWriter(Writer writer, String suffix) {
        super(writer, suffix);
    }

    @Override
    protected String makeSaveInstanceStateStart(String className, String parentFqcn) {
        return "  public static void saveInstanceState(" + className + " target, android.os.Bundle outState) {\n" +
                makeSuperSaveCall(parentFqcn);
    }

    private String makeSuperSaveCall(String parentFqcn) {
        return parentFqcn != null ? "    " + parentFqcn + suffix + ".saveInstanceState(target, outState);\n" : "";
    }

    @Override
    protected String makeRestoreInstanceStateStart(String className) {
        return "  public static void restoreInstanceState(" + className + " target, android.os.Bundle savedInstanceState) {\n" +
                "    if (savedInstanceState == null) {\n" +
                "      return;\n" +
                "    }\n";
    }

    @Override
    protected String makeRestoreInstanceStateEnd(String parentFqcn) {
        return parentFqcn != null ? "    " + parentFqcn + suffix + ".restoreInstanceState(target, savedInstanceState);\n" : "";
    }

    @Override
    protected String makeSaveInstanceStateEnd() {
        return "";
    }
}
