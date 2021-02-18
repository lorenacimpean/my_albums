package com.example.myalbums.utils

import io.reactivex.rxjava3.subjects.PublishSubject

/**
 * Created by ancamihesan on 2/18/21. Email anca.mihesan@rodeapps.com
 *
 */

class RxOnItemClickListener<T> {

    val rx: PublishSubject<T> = PublishSubject.create();

    fun onItemClick(item: T?) {
        rx.onNext(item);
    }

}