package com.lsj.linker;

/**
 * File description
 *
 * @author linshujie
 * @date 2022/3/22
 */
public class Linker {
    public interface BindCallback{
        void onBind();
        void onUnbind();
    }
}
