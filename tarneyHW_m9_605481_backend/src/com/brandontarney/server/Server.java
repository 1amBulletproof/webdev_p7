/** Server class
 * - Serve Hiking rates
 *
 * @author Brandon Tarney
 * @since 7/15/2017
 */
package com.brandontarney.server;

import com.brandontarney.bookingrate.BookingSummary;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.brandontarney.controller.BadQueryStringException;
import com.brandontarney.controller.BadRateException;
import com.brandontarney.controller.HikeQueryParser;
import com.brandontarney.controller.Controller;
import com.brandontarney.bookingrate.Rates;
import com.brandontarney.bookingrate.BookingSummary;

public class Server extends Thread {

    private final Socket socket;

    public Server(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String queryString = in.readLine();
            //System.out.println(queryString);
            Rates.HIKE hike = HikeQueryParser.getHike(queryString);
            int duration = HikeQueryParser.getDuration(queryString);
            int year = HikeQueryParser.getYear(queryString);
            int month = HikeQueryParser.getMonth(queryString);
            int day = HikeQueryParser.getDay(queryString);

            Rates rate = Controller.computeRate(hike, duration, year, month, day);

            out.println(rate.getCost() + ":" + "Quoted Rate");
            
        } catch (BadQueryStringException exception) {
            out.println("-0.01:Bad query string - " + exception.getMessage());
        } catch (BadRateException exception) {
            out.println("-0.01:Bad Rate - " + exception.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
