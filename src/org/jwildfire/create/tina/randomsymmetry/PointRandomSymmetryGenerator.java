/*
  JWildfire - an image and animation processor written in Java 
  Copyright (C) 1995-2014 Andreas Maschke

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
package org.jwildfire.create.tina.randomsymmetry;

import org.jwildfire.create.tina.base.Flame;
import org.jwildfire.create.tina.base.PostSymmetryType;

public class PointRandomSymmetryGenerator implements RandomSymmetryGenerator {

  @Override
  public void addSymmetry(Flame pFlame) {
    pFlame.setPostSymmetryType(PostSymmetryType.POINT);
    pFlame.setPostSymmetryCentreX(0.5 - Math.random());
    pFlame.setPostSymmetryCentreY(0.5 - Math.random());
    pFlame.setPostSymmetryOrder((int) (2.0 + Math.random() * 6.0));
  }

  @Override
  public String getName() {
    return "Point";
  }

}
