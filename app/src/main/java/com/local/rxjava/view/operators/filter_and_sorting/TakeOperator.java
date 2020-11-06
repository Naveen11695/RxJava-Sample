package com.local.rxjava.view.operators.filter_and_sorting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.local.rxjava.R;
import com.local.rxjava.model.Task;
import com.local.rxjava.utils.DummyDataSource;
import com.local.rxjava.utils.MyObserver;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TakeOperator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_operator);

        takeOperator().subscribe(new MyObserver<Task>());
    }

    /**
     * Return Observable Task with 3 entities (emissions)
     */
    private @NonNull Observable<Task> takeOperator() {
        return Observable
                .fromIterable(DummyDataSource.createTasksList())
                .take(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}