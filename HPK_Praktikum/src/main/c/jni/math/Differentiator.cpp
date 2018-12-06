//
// Created by sisi on 05.12.18.
//
#include <math.h>
#include <stdio.h>
#include <stdlib.h>

#include "Differentiator.h"
#include "../function/Function.h"
#include "Convergence.cpp"


int difCalls = 0;

int getDifCalls() {
    return difCalls;
}

void setDifCalls(int calls) {
    difCalls = calls;
}

double d(Function &f, double h, double x) {
    difCalls += 2;
    return f(x + h) - f(x - h);
}

double fh(Function &f, double h, double x) {
    double t1 = 8 * d(f, h, x);
    double t2 = d(f, 2 * h, x);
    double dn = 12 * h;
    return (t1 - t2) / dn;
}


double differentiate(Function &f, double x, double err) {
    int calls = 0;
    double oldDelta, delta;
    double h = 1.E-1;

    difCalls = 0;
    int counter = 0;
    double oldFh, fh;
    double oldRes, res;

    do {
        calls++;
        oldDelta = delta;
        delta = d(f, h, x);
        oldFh = fh;
        fh = (8 * delta - oldDelta) / (12 * h);
        oldRes = res;
        res = (16 * fh - oldFh) / 15;
        h *= 0.5;
        counter++;
    } while (convergence(res, oldRes, counter, err) == 0);

    return res;
}