package com.bramwork.myretro

import com.bramwork.myretro.board.Card
import com.bramwork.myretro.board.CardType
import com.bramwork.myretro.board.RetroBoard
import com.bramwork.myretro.exception.CardNotFoundException
import com.bramwork.myretro.exception.RetroBoardNotFoundException
import com.bramwork.myretro.service.RetroBoardService
import org.assertj.core.api.Assertions
import org.assertj.core.api.AssertionsForClassTypes
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.UUID

@SpringBootTest
internal class MyretroApplicationTests {
	@Autowired
	lateinit var service: RetroBoardService

	var retroBoardUUID = UUID.fromString("b79cb3ba-745e-5d9a-8903-4a02327a7e09")
	var cardUUID = UUID.fromString("20354d7a-e4fe-47af-8ff6-187bca92f3f9")
	var mehCardUUID = UUID.fromString("b11c9be1-b619-4ef5-be1b-a1cd9ef265b7")


	@Test
	fun saveRetroBoardTest() {
		val retroBoard = service.save(RetroBoard(name = "Gathering 2023"))
		Assertions.assertThat(retroBoard).isNotNull()
		Assertions.assertThat(retroBoard.id!!).isNotNull()
	}

	@Test
	fun findAllRetroBoardsTest() {
		val retroBoards = service.findAll()
		Assertions.assertThat(retroBoards).isNotEmpty()
		Assertions.assertThat(retroBoards).isNotEmpty()
	}

	@Test
	fun cardsRetroBoardNotFoundTest() {
		AssertionsForClassTypes.assertThatThrownBy {
			service.findAllCardsFromRetroBoard(UUID.randomUUID())
		}.isInstanceOf(RetroBoardNotFoundException::class.java)
	}

	@Test
	fun findRetroBoardTest() {
		val retroBoard = service.findById(retroBoardUUID)
		Assertions.assertThat(retroBoard).isNotNull()
		Assertions.assertThat(retroBoard.name).isEqualTo("Spring Boot 3.0 Meeting")
		Assertions.assertThat(retroBoard.id).isEqualTo(retroBoardUUID)
	}

	@Test
	fun addCardToRetroBoardTest() {
		val card = service.addCardToRetroBoard(
			retroBoardUUID, Card(
				comment = "Amazing session",
				cardType = CardType.HAPPY
			)
		)
		Assertions.assertThat(card).isNotNull()
		Assertions.assertThat(card.id).isNotNull()
		val retroBoard = service.findById(retroBoardUUID)
		Assertions.assertThat(retroBoard).isNotNull()
		Assertions.assertThat(retroBoard.cards).isNotEmpty()
	}

	@Test
	fun findAllCardsFromRetroBoardTest() {
		val cardList = service.findAllCardsFromRetroBoard(retroBoardUUID)
		Assertions.assertThat(cardList).isNotEmpty()
		Assertions.assertThat((cardList as Collection<*>).size).isGreaterThan(3)
	}

	@Test
	fun removeCardsFromRetroBoardTest() {
		service.removeCardFromRetroBoard(retroBoardUUID, cardUUID)
		val retroBoard = service.findById(retroBoardUUID)
		Assertions.assertThat(retroBoard).isNotNull()
		Assertions.assertThat(retroBoard.cards).isNotEmpty()
		Assertions.assertThat(retroBoard.cards).hasSizeLessThan(4)
	}

	@Test
	fun findCardByIdRetroBoardTest() {
		val card = service.findCardByUUIDFromRetroBoard(retroBoardUUID, mehCardUUID)
		Assertions.assertThat(card).isNotNull()
		Assertions.assertThat(card.id).isEqualTo(mehCardUUID)
	}

	@Test
	fun notFoundInRetroBoardTest() {
		AssertionsForClassTypes.assertThatThrownBy {
			service.findCardByUUIDFromRetroBoard(
				retroBoardUUID,
				UUID.randomUUID()
			)
		}.isInstanceOf(CardNotFoundException::class.java)
	}

}
