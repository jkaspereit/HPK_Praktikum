//
// Created by sisi on 05.12.18.
//
#include <stdio.h>
#include <math.h>

#include "../function/Function.h"
#include "Integrator.h"
#include "Convergence.cpp"

int intCalls = 0;
int MAX_CALLS = 12000;

int getIntCalls() {
    return intCalls;
}

double integrate(Function &f, double a, double b, double eps) {
    //*(-1) if b < a
    if (b < a) {
        return -1 * integrate(f, b, a, eps);
    }

    //setup
    intCalls = 0;
    int n = 1;
    double temp = 0.0;
    double result = 0.0;
    double h;
    double fa = f(a);
    double fb = f(b);
    intCalls += 2;
    int counter = 0;

    do {
        h = (b - a) / n;
        temp = result;
        double t4 = a + h / 2;
        double t2 = a + h;
        double sum1 = 0;
        double sum2 = f(t4);
        for (int j = 1; j < n; sum1 += f(t2), t2 += h, t4 += h, sum2 += f(t4), j++, intCalls += 2);
        result = (h / 6) * (fa + fb + 2 * sum1 + 4 * sum2);
        n = n * 2;
        counter++;
    } while (convergence(result, temp, counter, eps) == 0);

    return result;
}
