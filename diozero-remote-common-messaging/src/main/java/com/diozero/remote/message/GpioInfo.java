package com.diozero.remote.message;

/*-
 * #%L
 * Organisation: diozero
 * Project:      diozero - Remote Common
 * Filename:     GpioInfo.java
 * 
 * This file is part of the diozero project. More information about this project
 * can be found at https://www.diozero.com/.
 * %%
 * Copyright (C) 2016 - 2021 diozero
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import java.io.Serializable;
import java.util.Collection;

import com.diozero.api.DeviceMode;

public class GpioInfo implements Serializable {
	private static final long serialVersionUID = -2314562135065372805L;

	private String header;
	private int physicalPin;
	private int gpioNumber;
	private int sysFsNumber;
	private int chip;
	private int lineOffset;
	private String name;
	private Collection<DeviceMode> modes;
	private int pwmNum;

	public GpioInfo(String header, int physicalPin, int gpioNumber, int sysFsNumber, int chip, int lineOffset,
			String name, Collection<DeviceMode> modes, int pwmNum) {
		this.header = header;
		this.physicalPin = physicalPin;
		this.gpioNumber = gpioNumber;
		this.sysFsNumber = sysFsNumber;
		this.chip = chip;
		this.lineOffset = lineOffset;
		this.name = name;
		this.modes = modes;
		this.pwmNum = pwmNum;
	}

	public String getHeader() {
		return header;
	}

	public int getPhysicalPin() {
		return physicalPin;
	}

	public int getGpioNumber() {
		return gpioNumber;
	}

	public int getSysFsNumber() {
		return sysFsNumber;
	}

	public int getChip() {
		return chip;
	}

	public int getLineOffset() {
		return lineOffset;
	}

	public String getName() {
		return name;
	}

	public Collection<DeviceMode> getModes() {
		return modes;
	}

	public int getPwmNum() {
		return pwmNum;
	}
}
