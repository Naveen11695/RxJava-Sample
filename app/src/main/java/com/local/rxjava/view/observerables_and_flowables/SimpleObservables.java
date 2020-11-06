package com.local.rxjava.view.observerables_and_flowables;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.local.rxjava.R;
import com.local.rxjava.model.Task;
import com.local.rxjava.utils.DummyDataSource;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SimpleObservables extends AppCompatActivity {

    // A disposable remove the observer from an observable
    private static CompositeDisposable disposable = new CompositeDisposable();

    private static final String TAG = "ObservablesAndFlowables";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setObservable().subscribe(new MyObservable());
    }

    /**
     * fun to set the observable on background Thread
     */
    private Observable<Task> setObservable() {
        return Observable
                .fromIterable(DummyDataSource.createTasksList())
                .subscribeOn(Schedulers.io())
                .filter(task -> {
                    Log.d(TAG, "test: " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return task.isComplete();
                })
                .observeOn(AndroidSchedulers.mainThread());
    }



    /**
     * Observer class
     */
    static class MyObservable implements Observer<Task> {
        //          call on subscribing the observable
        @Override
        public void onSubscribe(@NonNull Disposable d) {
            Log.d(TAG, "onSubscribe: called");
            disposable.add(d);
        }

        //           call on the next iterable item on the observable list (Task list)
        @Override
        public void onNext(@NonNull Task task) {
            Log.d(TAG, "onNext: " + Thread.currentThread().getName());
            Log.d(TAG, "onNext: " + task.getDescription());
        }

        //           call on error occurs
        @Override
        public void onError(@NonNull Throwable e) {
            Log.e(TAG, "onError: called", e);
        }

        //           call after all the Task are completed
        @Override
        public void onComplete() {
            Log.d(TAG, "onComplete: completed..");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // It will clear all the subscriber of observers without disabling it -- soft clear
        disposable.clear();
        // It will no longer allow anything to subscribe to observable -- hard clear
        disposable.dispose();
    }
}