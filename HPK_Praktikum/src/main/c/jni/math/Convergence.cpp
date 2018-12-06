//
// Created by sisi on 05.12.18.
//

#include "Convergence.h"

#define NMAX 12

int convergence(double val, double valOld, int n, double eps) {

    if (n > NMAX) {
        throw "no convergence";
    }
    if (n >= 2) {
        if (fabs(val - valOld) < eps) {
            return true;
        }
        if (fabs((valOld - val) / ((val + valOld) / 2)) < eps) {
            return true;
        }
    }
    return false;
}