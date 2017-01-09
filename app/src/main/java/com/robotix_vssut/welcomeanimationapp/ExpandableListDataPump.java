package com.robotix_vssut.welcomeanimationapp;

/**
 * Created by Hp on 18-12-2016.
 */



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {


    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> Year_2016 = new ArrayList<String>();
        Year_2016.add("• 8 teams shortlisted for Eyantra funded by MHRD(IIT B)");
        Year_2016.add("• 1st prize in Drone Wars(NIT RKL)");
        Year_2016.add("• 1st prize in Tread-0-Quest(NIT RKL)");
        Year_2016.add("• 1st prize in Tread-0-Quest(NIT RKL)");
        Year_2016.add("• 2nd prize in Death Race(NIT RKL) ");


        List<String> Year_2015= new ArrayList<String>();
        Year_2015.add("• 3 teams shortlisted for Eyantra funded by MHRD(IIT B)");
        Year_2015.add("• AIR – 7 at Eyantra funded by MHRD(IIT B) ");
        Year_2015.add("• 3rd prize  in  ROBOTRYST(IIT DELHI) ");
        Year_2015.add("• Award for Best Design Bot at IIT KGP");
        Year_2015.add("• 3rd Position in Aerial event(Hoverpod) funded by ISRO");
        Year_2015.add("• 1st prize in Tread-0-Quest(NIT RKL)");
        Year_2015.add("• 2nd prize in Tread-0-Quest(NIT RKL)");
        Year_2015.add("• 1st prize in Roboventure(NIT RKL) ");


        List<String> Year_2014 = new ArrayList<String>();
          Year_2014.add("• 1st prize in Tread-0-Quest(NIT RKL)");
          Year_2014.add("• 2nd prize in Roboventure(NIT RKL)");
          Year_2014.add("• 1st Position in Aerial event(Hoverpod) funded by ISRO");
          Year_2014.add("• 3rd prize in in Water Event(Boyant)(IIT KGP)");


        List<String> Year_2013 = new ArrayList<String>();
        Year_2013.add("• 2nd Position in Underwater Robotics(NIT RKL)");
        Year_2013.add("• 3rd Position in Manual Robotics(NIT RKL)");

        List<String> Year_2012 = new ArrayList<String>();
        Year_2012.add("1st Position at IIT Bhubaneswar");
        Year_2012.add("• 1st Position at IIT Bhubaneswar");
        Year_2012.add("• 1st Position at NIT Rourkela");

        List<String> Year_2011 = new ArrayList<String>();
        Year_2011.add("1st Position at IIT Bhubaneswar");

        List<String> Year_2008 = new ArrayList<String>();
        Year_2008.add("• 1st Position at Kshitij IIT Kharagpur");
        Year_2008.add("• 2nd Position at NEXUS IIT Bombay");





        expandableListDetail.put("YEAR 2008", Year_2008);
        expandableListDetail.put("YEAR 2011", Year_2011);
        expandableListDetail.put("YEAR 2012", Year_2012);
        expandableListDetail.put("YEAR 2013", Year_2013);
        expandableListDetail.put("YEAR 2014", Year_2014);
        expandableListDetail.put("YEAR 2015", Year_2015);
        expandableListDetail.put("YEAR 2016", Year_2016);

        return expandableListDetail;
    }
}

