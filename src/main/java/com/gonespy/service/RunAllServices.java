package com.gonespy.service;

import com.gonespy.service.auth.AuthService;
import com.gonespy.service.availability.AvailabilityService;
import com.gonespy.service.gpcm.GPCMService;
import com.gonespy.service.gpsp.GPSPService;
import com.gonespy.service.sake.SakeService;
import com.gonespy.service.stats.GStatsService;

public class RunAllServices {

    public static void main(String[] args) {

        Thread availabilityServiceThread = new Thread(() -> new AvailabilityService().run());

        Thread gpcmServiceThread = new Thread(() -> new GPCMService().run());

        Thread gpspServiceThread = new Thread(() -> new GPSPService().run());

        Thread gstatsServiceThread = new Thread(() -> new GStatsService().run());

        Thread authServiceThread = new Thread(() -> AuthService.main(new String[]{"server", "resources/dw-auth-config.yml"}));

        Thread sakeServiceThread = new Thread(() -> SakeService.main(new String[]{"server", "resources/dw-sake-config.yml"}));

        gpcmServiceThread.start();
        availabilityServiceThread.start();
        gpspServiceThread.start();
        gstatsServiceThread.start();

        authServiceThread.start();
        DropwizardProbe.probeOnPort(443, true);

        sakeServiceThread.start();
        DropwizardProbe.probeOnPort(80, true);

        System.out.println("=== ALL SERVICES STARTED! ===");

    }



}
