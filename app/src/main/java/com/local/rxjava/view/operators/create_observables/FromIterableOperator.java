package com.local.rxjava.view.operators.create_observables;

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

public class FromIterableOperator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_iterable_operator);

        createObservablesUsing_fromIterable().subscribe(new MyObserver<Task>());
    }

    /**
     * create observer using fromIterable()
     */
    private @NonNull Observable<Task> createObservablesUsing_fromIterable() {
        return Observable
                .fromIterable(DummyDataSource.createTasksList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}