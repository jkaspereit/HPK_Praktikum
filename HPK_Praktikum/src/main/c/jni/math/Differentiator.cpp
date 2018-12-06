//
// Created by sisi on 05.12.18.
//
#include <math.h>
#include "Differentiator.h"

double calcDelta(Function &f, double h, double x) {

    return f(x + h) - f(x - h);
}

double differentiateSimple(Function &f, double x, double err) {
    double h = 0.1e-5;

    double result = ((f(x + h) - f(x - h)) / (2 * h));
    return result;
}

double differentiate(Function &f, double x, double err) {
    double delta = 0, deltaOld, derivH = 0, deriv2H, ret = 0, retOld;
    double h = 1.e-1;
    int count = 0;

    do {
        count++;
        deltaOld = delta;
        delta = calcDelta(f, h, x);
        deriv2H = derivH;
        derivH = (8 * delta - deltaOld) / (12 * h);
        retOld = ret;
        ret = (16 * derivH - deriv2H) / 15;
        h *= 0.5;
    } while (!convergence(ret, retOld, count, err));


    return ret;
}
