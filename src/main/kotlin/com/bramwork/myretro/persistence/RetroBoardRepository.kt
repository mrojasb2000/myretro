package com.bramwork.myretro.persistence

import com.bramwork.myretro.board.Card
import com.bramwork.myretro.board.CardType
import com.bramwork.myretro.board.RetroBoard
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class RetroBoardRepository : BaseRepository<RetroBoard, UUID> {
    private val retroBoardMap = mutableMapOf(
        UUID.fromString("b79cb3ba-745e-5d9a-8903-4a02327a7e09") to RetroBoard(
            id = UUID.fromString("b79cb3ba-745e-5d9a-8903-4a02327a7e09"),
            name = "Spring Boot 3.0 Meeting",
            cards = listOf(
                Card(id = UUID.fromString("20354d7a-e4fe-47af-8ff6-187bca92f3f9"),
                    comment = "Happy to meet the team", cardType = CardType.HAPPY),
                Card(id = UUID.fromString("bd2cbad1-6ccf-48e3-bb92-bc9961bc011e"),
                    comment = "New Projects", cardType = CardType.HAPPY),
                Card(id = UUID.fromString("b11c9be1-b619-4ef5-be1b-a1cd9ef265b7"),
                    comment = "When to meet again??", cardType = CardType.MEH),
                Card(id = UUID.fromString("caa8b54a-eb5e-4134-8ae2-a3946a428ec7"),
                    comment = "We need more time to finish", cardType = CardType.SAD)
            )
        )
    )

    override fun save(domain: RetroBoard): RetroBoard {
        domain.id = domain.id ?: UUID.randomUUID()
        retroBoardMap[domain.id] = domain
        return domain
    }

    override fun findById(uuid: UUID): RetroBoard = retroBoardMap[uuid]!!

    override fun findAll(): Iterable<RetroBoard> = retroBoardMap.values

    override fun delete(uuid: UUID) {
        retroBoardMap.remove(uuid)
    }


}