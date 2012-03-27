/*
  JWildfire - an image and animation processor written in Java 
  Copyright (C) 1995-2011 Andreas Maschke

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
package org.jwildfire.create.tina.variation;

import static org.jwildfire.base.MathLib.M_PI;
import static org.jwildfire.base.MathLib.cos;
import static org.jwildfire.base.MathLib.fabs;
import static org.jwildfire.base.MathLib.pow;
import static org.jwildfire.base.MathLib.sin;
import static org.jwildfire.base.MathLib.sqrt;

import org.jwildfire.create.tina.base.XForm;
import org.jwildfire.create.tina.base.XYZPoint;

public class GlynnSim2Func extends VariationFunc {

  private static final String PARAM_RADIUS = "radius";
  private static final String PARAM_THICKNESS = "thickness";
  private static final String PARAM_CONTRAST = "contrast";
  private static final String PARAM_POW = "pow";
  private static final String PARAM_PHI1 = "Phi1";
  private static final String PARAM_PHI2 = "Phi2";

  private static final String[] paramNames = { PARAM_RADIUS, PARAM_THICKNESS, PARAM_CONTRAST, PARAM_POW, PARAM_PHI1, PARAM_PHI2 };

  private double radius = 1.0;
  private double thickness = 0.1;
  private double contrast = 0.5;
  private double pow = 1.5;
  private double phi1 = 110.0;
  private double phi2 = 150.0;

  private class Point {
    private double x, y;
  }

  private void circle(FlameTransformationContext pContext, Point p) {
    double r = this.radius + this.thickness - this.Gamma * pContext.random();
    double Phi = this.Phi10 + this.Delta * pContext.random();
    double sinPhi = sin(Phi);
    double cosPhi = cos(Phi);
    p.x = r * cosPhi;
    p.y = r * sinPhi;
  }

  @Override
  public void transform(FlameTransformationContext pContext, XForm pXForm, XYZPoint pAffineTP, XYZPoint pVarTP, double pAmount) {
    /* GlynnSim2 by eralex61, http://eralex61.deviantart.com/art/GlynnSim-plugin-112621621 */
    double r = sqrt(pAffineTP.x * pAffineTP.x + pAffineTP.y * pAffineTP.y);
    double Alpha = this.radius / r;
    if (r < this.radius) {
      circle(pContext, toolPoint);
      pVarTP.x += pAmount * toolPoint.x;
      pVarTP.y += pAmount * toolPoint.y;
    }
    else {
      if (pContext.random() > this.contrast * pow(Alpha, this.absPow)) {
        pVarTP.x += pAmount * pAffineTP.x;
        pVarTP.y += pAmount * pAffineTP.y;
      }
      else {
        pVarTP.x += pAmount * Alpha * Alpha * pAffineTP.x;
        pVarTP.y += pAmount * Alpha * Alpha * pAffineTP.y;
      }
    }

  }

  @Override
  public String[] getParameterNames() {
    return paramNames;
  }

  @Override
  public Object[] getParameterValues() {
    return new Object[] { radius, thickness, contrast, pow, phi1, phi2 };
  }

  @Override
  public void setParameter(String pName, double pValue) {
    if (PARAM_RADIUS.equalsIgnoreCase(pName))
      radius = pValue;
    else if (PARAM_THICKNESS.equalsIgnoreCase(pName))
      thickness = limitVal(pValue, 0.0, 1.0);
    else if (PARAM_CONTRAST.equalsIgnoreCase(pName))
      contrast = limitVal(pValue, 0.0, 1.0);
    else if (PARAM_POW.equalsIgnoreCase(pName))
      pow = pValue;
    else if (PARAM_PHI1.equalsIgnoreCase(pName))
      phi1 = pValue;
    else if (PARAM_PHI2.equalsIgnoreCase(pName))
      phi2 = pValue;
    else
      throw new IllegalArgumentException(pName);
  }

  @Override
  public String getName() {
    return "glynnSim2";
  }

  private Point toolPoint = new Point();
  private double Phi10, Phi20, Gamma, Delta, absPow;

  @Override
  public void init(FlameTransformationContext pContext, XForm pXForm) {
    this.Phi10 = M_PI * this.phi1 / 180.0;
    this.Phi20 = M_PI * this.phi2 / 180.0;
    this.Gamma = this.thickness * (2.0 * this.radius + this.thickness) / (this.radius + this.thickness);
    this.Delta = this.Phi20 - this.Phi10;
    this.absPow = fabs(this.pow);
  }
}
