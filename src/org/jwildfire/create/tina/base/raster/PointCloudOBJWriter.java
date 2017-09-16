/*
  JWildfire - an image and animation processor written in Java 
  Copyright (C) 1995-2017 Andreas Maschke

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
package org.jwildfire.create.tina.base.raster;

import java.util.List;

import org.jwildfire.base.Tools;
import org.jwildfire.base.Unchecker;
import org.jwildfire.create.tina.base.raster.RasterPointCloud.PCPoint;

public class PointCloudOBJWriter {
  private boolean with1PointPolyGons = true;

  public void writeOBJ(List<PCPoint> pPoints, String pFilename) {
    StringBuilder sb = new StringBuilder();
    sb.append("####\r\n" +
        "#\r\n" +
        "# OBJ File Generated by " + Tools.APP_TITLE + "\r\n" +
        "#\r\n" +
        "####\r\n" +
        "# Object testc.obj\r\n" +
        "#\r\n" +
        "# Vertices: " + pPoints.size() + "\r\n" +
        "# Faces: " + (with1PointPolyGons ? pPoints.size() : 0) + "\r\n" +
        "#\r\n" +
        "####\r\n");
    for (PCPoint p : pPoints) {
      sb.append("v " + p.x + " " + p.y + " " + p.z + " " + p.r / 255.0 + " " + p.g / 255.0 + " " + p.b / 255.0 + "\r\n");
    }
    sb.append("# " + pPoints.size() + " vertices, 0 vertices normals\r\n");
    if (with1PointPolyGons) {
      for (int i = 0; i < pPoints.size(); i++) {
        sb.append("f " + (i + 1) + "\r\n");
      }
    }

    sb.append("# " + (with1PointPolyGons ? pPoints.size() : 0) + " faces, 0 coords texture\r\n");
    sb.append("# End of File\r\n");

    try {
      Tools.writeUTF8Textfile(pFilename, sb.toString());
    }
    catch (Exception ex) {
      Unchecker.rethrow(ex);
    }
  }

}