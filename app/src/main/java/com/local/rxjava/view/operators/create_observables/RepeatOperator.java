package com.local.rxjava.view.operators.create_observables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.local.rxjava.R;
import com.local.rxjava.utils.MyObserver;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RepeatOperator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat_operator);

        createObservablesUsingRepeatMethod().subscribe(new MyObserver<Integer>());
    }

    /**
     * Create observables using range() and repeat() operators
     */
    private @NonNull Observable<Integer> createObservablesUsingRepeatMethod() {
        return Observable.range(0,2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeat(3);
    }
}