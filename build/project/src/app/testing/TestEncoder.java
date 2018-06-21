package app.testing;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import app.Encoder;

public class TestEncoder {
	
	Random r = new Random();

	@Test
	public void test1() {
		//Testing encryption/decryption with code.len == str.len

		String code = "";
		String str = "";
		for(int i=0;i<10000;i++) {
			code += (char)(r.nextInt(200));
			str += (char)(r.nextInt(200));
		}
		
		Encoder encoder = new Encoder(code);
		
		String enc = encoder.encrypt(str);
		String dec = encoder.decrypt(enc);
		
		assertFalse(dec.equals(encoder.decrypt(str)));
		assertTrue(enc.equals(encoder.encrypt(str)));
		assertTrue(dec.equals(encoder.decrypt(enc)));
		
	}
	
	@Test
	public void test2() {
		//Testing encryption/decription where code.len > str.len

		String code = "";
		String str = "";
		for(int i=0;i<10000;i++) {
			code += (char)(r.nextInt(200));
			if(i<20) 
				str += (char)(r.nextInt(200));
		}
		
		Encoder encoder = new Encoder(code);
		
		String enc = encoder.encrypt(str);
		String dec = encoder.decrypt(enc);
		
		assertFalse(dec.equals(encoder.decrypt(str)));
		assertTrue(enc.equals(encoder.encrypt(str)));
		assertTrue(dec.equals(encoder.decrypt(enc)));
	}
	
	@Test
	public void test3() {
		//Testing encryption/decription where code.len < str.len

		String code = "";
		String str = "";
		for(int i=0;i<10000;i++) {
			str += (char)(r.nextInt(200));
			if(i<20) 
				code += (char)(r.nextInt(200));
		}
		
		Encoder encoder = new Encoder(code);
		
		String enc = encoder.encrypt(str);
		String dec = encoder.decrypt(enc);
		
		assertFalse(dec.equals(encoder.decrypt(str)));
		assertTrue(enc.equals(encoder.encrypt(str)));
		assertTrue(dec.equals(encoder.decrypt(enc)));
	}

	@Test(expected = NullPointerException.class)
	public void testStrNull() {

		//code null
		Encoder encoder = new Encoder("code");

		encoder.decrypt(null);
		
	}
	
	@Test(expected = NullPointerException.class)
	public void testCodeNull() {

		//code null
		Encoder encoder = new Encoder(null);
		
		encoder.encrypt("abcd");
		
	}
	
}
