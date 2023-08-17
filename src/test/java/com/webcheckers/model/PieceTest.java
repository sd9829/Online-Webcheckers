package com.webcheckers.model;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.model.Piece;
import com.webcheckers.model.Piece.COLOR;
import com.webcheckers.model.Piece.TYPE;

@Testable
public class PieceTest {

	@Test
	public void testGetType() {
		Piece singleWhite = new Piece(TYPE.SINGLE, COLOR.WHITE);
		TYPE expected = new Piece(TYPE.SINGLE, COLOR.WHITE).getType();
		
		assertEquals(expected, singleWhite.getType());		
	}
	
	@Test
	public void testGetColor() {
		Piece singleWhite = new Piece(TYPE.SINGLE, COLOR.WHITE);
		COLOR expected = new Piece(TYPE.SINGLE, COLOR.WHITE).getColor();
		
		assertEquals(expected, singleWhite.getColor());
	}
	
	@Test
	public void testEquals() {
		Piece expected = new Piece(TYPE.SINGLE, COLOR.WHITE);
		
		assertTrue(expected.equals(new Piece(TYPE.SINGLE, COLOR.WHITE)));
	}
}
