/*
  JWildfire - an image and animation processor written in Java 
  Copyright (C) 1995-2013 Andreas Maschke

  This is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser 
  General Public License as published by the Free Software Foundation; either version 2.1 of the 
  License, or (at your option) any later version.
 
  This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
  even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License along with this software; 
  if not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
  02110-1301 USA, or see the FSF site: http://www.fsf.org.
*/
package org.jwildfire.base;

import odk.lang.FastMath;

public final class MathLib {
  public final static double SMALL_EPSILON = 1.0e-300;
  public static final double EPSILON = 0.00000001;
  public final static int TRUE = 1;
  public final static int FALSE = 0;
  public final static double M_PI = Math.PI;
  public final static double M_PI_2 = M_PI * 0.5;
  public final static double M_PI_4 = M_PI * 0.25;
  public final static double M_1_PI = 1.0 / M_PI;
  public final static double M_2_PI = 2.0 / M_PI;
  public static final double M_2PI = 2.0 * M_PI;

  public static final int iabs(int var) {
    if (var >= 0)
      return var;
    else
      return 0 - var;
  }

  public static final double fabs(double var) {
    if (var >= 0.0)
      return var;
    else
      return 0.0 - var;
  }

  public static final int sign(double val) {
    if (val > 0.0)
      return 1;
    else if (val < 0.0)
      return -1;
    else
      return 0;
  }

  public static final double sin(double a) {
    return FastMath.sin(a);
  }

  public static final double cos(double a) {
    return FastMath.cos(a);
  }

  public static final double tan(double a) {
    return FastMath.tan(a);
  }

  public static final double fmod(double a, double b) {
    return a % b;
  }

  public static final double sqr(double a) {
    return a * a;
  }

  public static final double atan2(double y, double x) {
    return FastMath.atan2(y, x);
  }

  public static final double exp(double a) {
    return FastMath.exp(a);
  }

  public static final double sqrt(double a) {
    return Math.sqrt(a);
  }

  public static final double pow(double value, double power) {
    return FastMath.pow(value, power);
  }

  public static final double floor(double value) {
    return FastMath.floor(value);
  }

  public static final double round(double value) {
    return FastMath.round(value);
  }

  public static final double log10(double value) {
    return Math.log10(value);
  }

  public static final double log(double value) {
    return FastMath.log(value);
  }

  public static final double sinh(double value) {
    return FastMath.sinh(value);
  }

  public static final double cosh(double value) {
    return FastMath.cosh(value);
  }

  public static final double tanh(double value) {
    return FastMath.tanh(value);
  }

  public static final double min(double a, double b) {
    return Math.min(a, b);
  }

  public static final double max(double a, double b) {
    return Math.max(a, b);
  }

  public static final double atan(double value) {
    return FastMath.atan(value);
  }

  public static final double acos(double value) {
    return FastMath.acos(value);
  }

  public static final double asin(double value) {
    return FastMath.asin(value);
  }

  public static final double rint(double value) {
    return Math.rint(value);
  }

  public static final double trunc(double value) {
    return (value < 0) ? Math.ceil(value) : Math.floor(value);
  }

  public static final double frac(double value) {
    return value - trunc(value);
  }

  public static final double acosh(double d) {
    return log(sqrt(pow(d, 2.0) - 1.0) + d);
  }

  // Quick erf code taken from http://introcs.cs.princeton.edu/java/21function/ErrorFunction.java.html
  // Copyright � 2000�2010, Robert Sedgewick and Kevin Wayne. 
  //fractional error in math formula less than 1.2 * 10 ^ -7.
  // although subject to catastrophic cancellation when z in very close to 0
  // from Chebyshev fitting formula for erf(z) from Numerical Recipes, 6.2
  public static double erf(double z) {
    double t = 1.0 / (1.0 + 0.5 * Math.abs(z));

    // use Horner's method
    double ans = 1 - t * Math.exp(-z * z - 1.26551223 +
        t * (1.00002368 +
        t * (0.37409196 +
            t * (0.09678418 +
                t * (-0.18628806 +
                    t * (0.27886807 +
                        t * (-1.13520398 +
                            t * (1.48851587 +
                                t * (-0.82215223 +
                                    t * (0.17087277))))))))));
    if (z >= 0)
      return ans;
    else
      return -ans;
  }

}
