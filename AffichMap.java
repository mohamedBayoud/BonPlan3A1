/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.maps.Coord;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.serviceBP;
import com.mycompany.Entite.BP;
import java.io.IOException;


/**
 *
 * @author Jasser
 */
public class AffichMap {
    private Form current;
	

    public void init(Object context) {
        try {
            Resources theme = Resources.openLayered("/theme_1");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void start(String s) {
        if(current != null){
            current.show();
            return;
        }
		System.out.println(s);
		BP bp = new BP();
		serviceBP b = new serviceBP();
        Form hi = new Form("Localisez votre hôtel ");
        hi.setLayout(new BorderLayout());
        final MapContainer cnt = new MapContainer();
        cnt.setMapType(MapContainer.MAP_TYPE_TERRAIN);
        cnt.setShowMyLocation(true);
		if (s.equals("tunis"))
		{	
       cnt.zoom(new Coord(36.8064948, 10.181531599999971),9);
		}
		else if (s.equals("nabeul"))
		{	
       cnt.zoom(new Coord(36.4512893, 10.735663400000021),9);
		}
		else if (s.equals("touzeur"))
		{	
       cnt.zoom(new Coord(33.918534, 8.122932900000023),9);
		}
		
        hi.addComponent(BorderLayout.CENTER, cnt);
		hi.getToolbar().addCommandToLeftBar("<", null, (j) -> {
                    AffichageBP h = new AffichageBP();
                    h.getF().show();
                  });
        /*hi.addCommand(new Command("Move Camera") {
            public void actionPerformed(ActionEvent ev) {
                cnt.setCameraPosition(new Coord(30.2548888,10.355484));
                   Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
            }
        });*/
        /*hi.addCommand(new Command("Add Marker") {
            public void actionPerformed(ActionEvent ev) {
                try {
                    cnt.setCameraPosition(new Coord(36.69912, 10.35078));
                    cnt.addMarker(EncodedImage.create("/market.png"), new Coord(36.69912,10.35078), "Hi marker", "Optional long description", new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                             ToastBar.showMessage("Abonnée à la boutique ", FontImage.MATERIAL_CAKE);
                            System.out.println("mmmm");
                        }
                    });
                } catch(IOException err) {
                    // since the image is iin the jar this is unlikely
                    err.printStackTrace();
                }
            }
        });*/
       /* hi.addCommand(new Command("Add Path") {
            public void actionPerformed(ActionEvent ev) {
                cnt.setCameraPosition(new Coord(36.69912, 10.35078));
                cnt.addPath( // Sydney
                    new Coord(36.83475079936988, 10.181479767089854),  // Fiji
                    new Coord(36.69912, 10.35078),  // Hawaii
                    new Coord(30.2548888,10.355484)  // Mountain View
                );
            }
        });
        hi.addCommand(new Command("Vider Carte") {
            public void actionPerformed(ActionEvent ev) {
                cnt.clearMapLayers();
            }
        });*/
        
        hi.show();
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }
}
