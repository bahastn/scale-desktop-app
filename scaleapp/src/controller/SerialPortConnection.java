
package controller;

import javafx.application.Platform;
import jssc.SerialPort;
import jssc.SerialPortException;

public class SerialPortConnection implements Runnable {
    static SerialPort serialPort = new SerialPort("COM9");
    private int value;

    public SerialPortConnection() {
    }
    //

    public void reaingSerial() throws SerialPortException {
        this.value = 0;
        this.setValue(this.value);

        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
            if (serialPort.isOpened()) {
                this.clarPort();
                Thread t = new Thread();
                t.start();

                try {
                    byte[] buffer = serialPort.readBytes(10);
                    String output = new String(buffer);
                    String integerValue = output.substring(1, 9).trim();
                    if (this.isValid(integerValue)) {
                        value = Integer.valueOf(integerValue);
                        setValue(this.value);
                    }
                } catch (SerialPortException var4) {
                    var4.printStackTrace();
                }
            }

        } catch (Exception var6) {
        }
    }


    public void dicConnectSerialPOrt() {
        try {
            Thread.sleep(100L);
            serialPort.closePort();
        } catch (SerialPortException var2) {
            var2.printStackTrace();
        } catch (InterruptedException var3) {
            var3.printStackTrace();
        }

    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isValid(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException var3) {
            return false;
        }
    }


    public boolean clarPort() {
        try {
            serialPort.purgePort(8);
            serialPort.purgePort(4);
            return true;
        } catch (SerialPortException var2) {
            var2.printStackTrace();
            return false;
        }
    }

    @Override
    public void run() {
        try {
            writeToPort();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public boolean writeToPort() throws SerialPortException {
        if (serialPort.isOpened()) {
            try {
                serialPort.setEventsMask(2);
                serialPort.writeBytes("p\r".getBytes());
                return true;
            } catch (Exception var2) {
                var2.getCause().getClass().getName();
            } finally {
                dicConnectSerialPOrt();
            }
        }
        return false;
    }
}

