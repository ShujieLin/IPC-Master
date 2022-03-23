package com.lsj.ipc_master.service;

import com.codezjx.andlinker.annotation.RemoteInterface;

/**
 * File description
 *
 * @author linshujie
 * @date 2022/3/22
 */
//@RemoteInterface
@RemoteInterface
public interface IRemoteService {
    int getPid();
}
