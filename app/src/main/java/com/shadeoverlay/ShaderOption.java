package com.shadeoverlay;

public class ShaderOption {
    public final String id;
    public final String name;
    public ShaderOption(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public static ShaderOption[] all() {
        return new ShaderOption[]{
            new ShaderOption("none", "بدون تأثير"),
            new ShaderOption("dim", "تعتيم"),
            new ShaderOption("warm", "دافئ"),
            new ShaderOption("cool", "بارد"),
            new ShaderOption("gradient", "تدرج"),
            new ShaderOption("pulse", "نبض"),
            new ShaderOption("scan", "خطوط"),
            new ShaderOption("gray", "رمادي")
        };
    }
}
