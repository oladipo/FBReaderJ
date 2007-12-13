package org.zlibrary.core.options;

import org.zlibrary.core.options.util.*;

/**
 * ����� ����� �����. ������ ����� ���� �������� ����� ������, ����� ���������
 * ������� �� ������ (� ��� ����! =)), �� ��������� � ���� ����� ��� �����
 * �������������� � ������� ������ ZLColor, ������� ��������� ����� ���������
 * RGB ������������� �����.
 * 
 * @author �������������
 * 
 */
public final class ZLColorOption extends ZLOption {

	private int myIntValue;

	private final int myDefaultValue;

	public ZLColorOption(String category, String group, String optionName,
			ZLColor defaultValue) {
		super(category, group, optionName);
		if (defaultValue != null) {
			myDefaultValue = defaultValue.getIntValue();
		} else {
			myDefaultValue = 0;
		}
		myIntValue = myDefaultValue;
	}

	public ZLColor getValue() {
		if (!myIsSynchronized) {
			String value = getConfigValue(null);
			if (value != null) {
				try {
					Integer intValue = Integer.parseInt(value);
					myIntValue = intValue;
				} catch (NumberFormatException e) {
					// System.err.println(e);
				}
			}
			myIsSynchronized = true;
		}
		return new ZLColor(myIntValue);
	}

	public void setValue(ZLColor colorValue) {
		if (colorValue != null) {
			int value = colorValue.getIntValue();
			if (myIsSynchronized && (myIntValue == value)) {
				return;
			}
			myIntValue = value;
			myIsSynchronized = true;
			if (myIntValue == myDefaultValue) {
				unsetConfigValue();
			} else {
				setConfigValue("" + myIntValue);
			}
		}
	}
}
