package com.helsanf.jetpacksubmision.utils

import com.helsanf.jetpacksubmision.model.Movie
import com.helsanf.jetpacksubmision.model.TvShow

class FakeDataDummy {

    fun generateMovie(): ArrayList<Movie> {
        val movie = ArrayList<Movie>()
        movie.add(
            Movie(
                "1",
                "joker",
                "8.6",
                "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.",
                "2019-10-02",
                "https://image.tmdb.org/t/p/w500/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg"
            )
        )

        movie.add(
            Movie(
                "2",
                "Terminator: Dark Fate",
                "6.4",
                "More than two decades have passed since Sarah Connor prevented Judgment Day, changed the future, and re-wrote the fate of the human race. Dani Ramos is living a simple life in Mexico City with her brother and father when a highly advanced and deadly new Terminator – a Rev-9 – travels back through time to hunt and kill her. Dani's survival depends on her joining forces with two warriors: Grace, an enhanced super-soldier from the future, and a battle-hardened Sarah Connor. As the Rev-9 ruthlessly destroys everything and everyone in its path on the hunt for Dani, the three are led to a T-800 from Sarah’s past that may be their last best hope.",
                "2019-10-23",
                "https://image.tmdb.org/t/p/w500/vqzNJRH4YyquRiWxCCOH0aXggHI.jpg"
            )
        )

        movie.add(
            Movie(
                "3",
                "Maleficent: Mistress of Evil",
                "7.2",
                "Maleficent and her goddaughter Aurora begin to question the complex family ties that bind them as they are pulled in different directions by impending nuptials, unexpected allies, and dark new forces at play.",
                "2019-10-16",
                "https://image.tmdb.org/t/p/w500/tBuabjEqxzoUBHfbyNbd8ulgy5j.jpg"
            )
        )

        movie.add(
            Movie(
                "4",
                "Dora and the Lost City of Gold",
                "6.5",
                "Dora, a girl who has spent most of her life exploring the jungle with her parents, now must navigate her most dangerous adventure yet: high school. Always the explorer, Dora quickly finds herself leading Boots (her best friend, a monkey), Diego, and a rag tag group of teens on an adventure to save her parents and solve the impossible mystery behind a lost Inca civilization.",
                "2019-08-08",
                "https://image.tmdb.org/t/p/w500/xvYCZ740XvngXK0FNeSNVTJJJ5v.jpg"
            )
        )

        movie.add(
            Movie(
                "5",
                "Frozen II",
                "7.1",
                "Elsa, Anna, Kristoff and Olaf are going far in the forest to know the truth about an ancient mystery of their kingdom.",
                "2019-11-11",
                "https://image.tmdb.org/t/p/w500/qdfARIhgpgZOBh3vfNhWS4hmSo3.jpg"
            )
        )

        movie.add(
            Movie(
                "6",
                "Doctor Sleep",
                "7.1",
                "A traumatized, alcoholic Dan Torrance meets Abra, a kid who also has the ability to \"shine.\" He tries to protect her from the True Knot, a cult whose goal is to feed off of people like them in order to remain immortal.",
                "2019-10-30",
                "https://image.tmdb.org/t/p/w500/p69QzIBbN06aTYqRRiCOY1emNBh.jpg"
            )
        )

        movie.add(
            Movie(
                "7",
                "Let It Snow",
                "6.4",
                "When a huge blizzard (that doesn't show signs of stopping) hits, Gracetown is completely snowed in. But even though it's cold outside, things are heating up inside, proving that Christmas is magical when it comes to love.",
                "2019-11-08",
                "https://image.tmdb.org/t/p/w500/rEVu3E1OhMPJeCAn9a6QLyVhZDj.jpg"
            )
        )

        movie.add(
            Movie(
                "8",
                "El Camino: A Breaking Bad Movie",
                "7.1",
                "In the wake of his dramatic escape from captivity, Jesse Pinkman must come to terms with his past in order to forge some kind of future.",
                "2019-10-11",
                "https://image.tmdb.org/t/p/w500/ePXuKdXZuJx8hHMNr2yM4jY2L7Z.jpg"
            )
        )

        movie.add(
            Movie(
                "9",
                "The King",
                "7.2",
                "England, 15th century. Hal, a capricious prince who lives among the populace far from court, is forced by circumstances to reluctantly accept the throne and become Henry V.",
                "2019-10-11",
                "https://image.tmdb.org/t/p/w500/8u0QBGUbZcBW59VEAdmeFl9g98N.jpg\n"
            )
        )

        movie.add(
            Movie(
                "10",
                "47 Meters Down: Uncaged",
                "5",
                "A group of backpackers diving in a ruined underwater city discover that they have stumbled into the territory of the ocean's deadliest shark species.",
                "2019-08-15",
                "https://image.tmdb.org/t/p/w500/g4z7mDmJmx23vsVg6XNWcnXb6gc.jpg"
            )
        )
        return movie
    }

    fun generateDummyTvShow(): ArrayList<TvShow> {
        val tvShow = ArrayList<TvShow>()

        tvShow.add(
            TvShow(
                "1",
                "Band of Brothers",
                "8.2",
                "Drawn from interviews with survivors of Easy Company, as well as their journals and letters, Band of Brothers chronicles the experiences of these men from paratrooper training in Georgia through the end of the war. As an elite rifle company parachuting into Normandy early on D-Day morning, participants in the Battle of the Bulge, and witness to the horrors of war, the men of Easy knew extraordinary bravery and extraordinary fear - and became the stuff of legend. Based on Stephen E. Ambrose's acclaimed book of the same name.",
                "2001-09-09",
                "https://image.tmdb.org/t/p/w500/yjqu0KEVAWz9eJQBvzrQMFlGD84.jpg"
            )
        )

        tvShow.add(
            TvShow(
                "2",
                "I Am Not an Animal",
                "9.5",
                "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists.",
                "2004-05-10",
                "https://image.tmdb.org/t/p/w500/nMhv6jG5dtLdW7rgguYWvpbk0YN.jpg"
            )
        )

        tvShow.add(
            TvShow(
                "3",
                "Chernobyl",
                "8.7",
                "The true story of one of the worst man-made catastrophes in history: the catastrophic nuclear accident at Chernobyl. A tale of the brave men and women who sacrificed to save Europe from unimaginable disaster.",
                "2019-05-06",
                "https://image.tmdb.org/t/p/w500/hlLXt2tOPT6RRnjiUmoxyG1LTFi.jpg"
            )
        )

        tvShow.add(
            TvShow(
                "4",
                "Rick and Morty",
                "8.6",
                "Rick is a mentally-unbalanced but scientifically-gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.",
                "2013-12-02",
                "https://image.tmdb.org/t/p/w500/qJdfO3ahgAMf2rcmhoqngjBBZW1.jpg"
            )
        )

        tvShow.add(
            TvShow(
                "5",
                "Breaking Bad",
                "8.4",
                "When Walter White, a New Mexico chemistry teacher, is diagnosed with Stage III cancer and given a prognosis of only two years left to live. He becomes filled with a sense of fearlessness and an unrelenting desire to secure his family's financial future at any cost as he enters the dangerous world of drugs and crime.",
                "2008-01-20",
                "https://image.tmdb.org/t/p/w500/1yeVJox3rjo2jBKrrihIMj7uoS9.jpg"
            )
        )

        tvShow.add(
            TvShow(
                "6",
                "Sherlock",
                "8.3",
                "A modern update finds the famous sleuth and his doctor partner solving crime in 21st century London.",
                "2010-07-25",
                "https://image.tmdb.org/t/p/w500/f9zGxLHGyQB10cMDZNY5ZcGKhZi.jpg"
            )
        )

        tvShow.add(
            TvShow(
                "7",
                "Planet Earth II",
                "8.3",
                "David Attenborough presents a documentary series exploring how animals meet the challenges of surviving in the most iconic habitats on earth.",
                "2016-11-06",
                "https://image.tmdb.org/t/p/w500/uy5QoTu8fc6fGXMCTMbpQJFUEB0.jpg"
            )
        )

        tvShow.add(
            TvShow(
                "8",
                "Avatar: The Last Airbender",
                "8.3",
                "In a war-torn world of elemental magic, a young boy reawakens to undertake a dangerous mystic quest to fulfill his destiny as the Avatar, and bring peace to the world.",
                "2005-02-21",
                "https://image.tmdb.org/t/p/w500/sB8V0pQtJZ17v8FLXMOcYz6045c.jpg"
            )
        )

        tvShow.add(
            TvShow(
                "9",
                "Stranger Things",
                "8.3",
                "When a young boy vanishes, a small town uncovers a mystery involving secret experiments, terrifying supernatural forces, and one strange little girl.",
                "2016-07-15",
                "https://image.tmdb.org/t/p/w500/x2LSRK2Cm7MZhjluni1msVJ3wDF.jpg"
            )
        )

        tvShow.add(
            TvShow(
                "10",
                "DEATH NOTE",
                "8.3",
                "Light Yagami is an ace student with great prospects—and he’s bored out of his mind. But all that changes when he finds the Death Note, a notebook dropped by a rogue Shinigami death god. Any human whose name is written in the notebook dies, and Light has vowed to use the power of the Death Note to rid the world of evil. But will Light succeed in his noble goal, or will the Death Note turn him into the very thing he fights against?",
                "2006-10-04",
                "https://image.tmdb.org/t/p/w500/g8hHbsRmHYoWYizhWCk87vpkrmy.jpg"
            )
        )


        return tvShow

    }

    fun getDetailMovie(moveId : String) : Movie?{
        for (i in 0 until generateMovie().size) {
            val movie = generateMovie().get(i)
            if (movie.movieId.equals(moveId)) {
                return movie
            }
        }
        return null
    }

    fun getDetailTvShow(tvShow : String) : TvShow?{
        for (i in 0 until generateMovie().size) {
            val tv = generateDummyTvShow().get(i)
            if (tv.idTvShow.equals(tvShow)) {
                return tv
            }
        }
        return null
    }
}