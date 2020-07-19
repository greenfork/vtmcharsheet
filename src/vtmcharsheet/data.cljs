(ns vtmcharsheet.data)

(def clans
  (array-map
   :brujah
   {:name "Brujah"
    :description "Rebellious and short-tempered type."
    :archetypes
     [{:name "Cancer in the system"
       :description "Sabotage the system from within!"}
      {:name "Voice of the people"
       :description "I must speak for the people to resist the power!"}
      {:name "Blood worshipper"
       :description "Bow to my superiority or die in your ignorance!"}
      {:name "Trolling punk"
       :description "What are you looking at, got a problem?!"}
      {:name "Monster in disguise"
       :description "I'm still human and I can still live a healthy life!"}]
    :disciplines ["Celerity" "Potence" "Presence"]
    :bane "Short-tempered and explosive they take penalty on rolls to resist fury frenzy."}
   :gangrel
   {:name "Gangrel"
    :description "The beast within us is wild and free."
    :archetypes
    [{:name "Uncaged jailbird"
      :description "Finally free, I will take all the freedom I deserve."}
     {:name "Adventurer"
      :description "My home is sky, grass, trees, I live by the rules of nature."}
     {:name "Folk favorite"
      :description "Stories hold immense knowledge and knowledge is power."}
     {:name "Director of the board"
      :description "Assertive dominance is the only way to power in this world."}
     {:name "Unrepentant beast"
      :description "Everyone is a beast, embrace and live with it or die!"}]
    :disciplines ["Animalism" "Fortitude" "Protean"]
    :bane "Beast-like features emerge while in frenzy negatively affecting interactions."}
   :malkavian
   {:name "Malkavian"
    :description "The labyrinths of mind are our domain."
    :archetypes
    [{:name "Influencing presence"
      :description "Look at me, look at me! Do you like what you see?"}
     {:name "Medium"
      :description "One hears voices if one listens closely enough..."}
     {:name "Bad analyst"
      :description "I hear your thoughts, I taste your emotions, you are already mine!"}
     {:name "Fanatic"
      :description "You lie, you lie! I know the truth, just open your eyes!"}
     {:name "Pure blood addict"
      :description "I need to take this meal one more time, please, I'm so weak..."}]
    :disciplines ["Auspex" "Dominate" "Obfuscate"]
    :bane "Sometimes visions haunt you making it hard to function in this world"}
   :nosferatu
   {:name "Nosferatu"
    :description "Night is your friend, if you know what is hiding in it."
    :archetypes
    [{:name "Information hub"
      :description "Information is power, I have power, I know people."}
     {:name "Domain gumshoe"
      :description "Shadow and dark corners, help me uncover this mystery..."}
     {:name "More animal than man"
      :description "Pss-pss.. little rattie, come here, I'm your friend"}
     {:name "Hunter of monsters"
      :description "No one expects me to smash their skull, often their last mistake."}
     {:name "Rat"
      :description "People come, people go, but I survive everyone!.."}]
    :disciplines ["Animalism" "Obfuscate" "Potence"]
    :bane "Don't try to be a human, your monstrous nature is easily seen."}
   :toreador
   {:name "Toreador"
    :description "The art and beauty is as much a weapon as manipulation and deceit."
    :archetypes
    [{:name "l'Artiste"
      :description "Beauty and art is the music of this life, darling."}
     {:name "Stage manager"
      :description "The night is long and I will help it to be everyone's best!"}
     {:name "Gadabout"
      :description "Weak people are just instruments, smart people control the weak."}
     {:name "Patron of the arts"
      :description "A diamond needs polishing before it becomes a brilliant!"}
     {:name "Thespian spy"
      :description "Secrets and information is my art!"}]
    :disciplines ["Auspex" "Celerity" "Presence"]
    :bane "Beauty is addictive, you suffer when being in ugly surroundings."}
   :tremere
   {:name "Tremere"
    :description "Our art is of distilling and devouring the knowledge of this world."
    :archetypes
    [{:name "Pyramid loyalist"
      :description "Pyramid is the only way to our lost power."}
     {:name "Eternal scholar"
      :description "Knowledge is what makes everything work, only a fool doesn't have it."}
     {:name "Pagan nonconformist"
      :description "Traditions are old and bleak, the true knowledge is now."}
     {:name "Ambitious outsider"
      :description "My way is my way and I will follow it as I want."}
     {:name "Chief of security"
      :description "The sharp mind is what makes a perfect investigator."}]
    :disciplines ["Auspex" "Blood sorcery" "Dominate"]
    :bane "The blood once strong is now weak and doesn't bind mortals as well."}
   :ventrue
   {:name "Ventrue"
    :description "Power and money is what makes one great in this world."
    :archetypes
    [{:name "Cold-blooded corporate director"
      :description "Problems don't solve themselves."}
     {:name "Member of the order"
      :description "Respected inside a respectful circle."}
     {:name "Conservative politician"
      :description "Young are stupid, but I must be strong to lead this people."}
     {:name "Godfather"
      :description "My men is my hands."}
     {:name "High priest"
      :description "My ancestors will be proud of me!"}]
    :disciplines ["Dominate" "Fortitude" "Presence"]
    :bane "The blood you drink should be of highest quality."}
   :caitiff
   {:name "Caitiff"
    :description "A Kindred with no clan, a lost soul."
    :archetypes
    [{:name "Raised on the streets"
      :description "I know the rules of this life."}
     {:name "Abusive survivor"
      :description "Should I revenge all my abusers?"}
     {:name "Helplessly overestimated"
      :description "I have no idea what I am and what I should do."}
     {:name "Secret caitiff"
      :description "Everyone survives as one can, don't judje me."}
     {:name "Unwanted childe"
      :description "Never knew my sire, never wanted to know."}]
    :disciplines ["Any"]
    :bane "Not respected by Kindred and having increased cost for leveling skills."}
   :thin-blooded
   {:name "Thin-blooded"
    :description "Too weak for a true vampire, too strong for a true human."
    :archetypes
    [{:name "Live one"
      :description "Nothing changed, I will live my normal life as nothing happened."}
     {:name "Weapon of convenience"
      :description "My sire finally freed me of this miserable life."}
     {:name "Guilty embrace"
      :description "My sire saved me, or did they?"}
     {:name "Natural vampire"
      :description "I wanted to be a real vampire, not this weak image of a real power."}
     {:name "Redemption seeker"
      :description "They will never make me do what they want, revenge is coming."}]
    :disciplines ["Thin-blood alchemy"]
    :bane "None"}))

(def skill-distributions
  {:jack-of-all-trades
   {:name "Jack-of-all-trades"
    :description "Take one skill at 3; eight skills at 2; ten skills at 1."}
   :balanced
   {:name "Balanced"
    :description "Take three skills at 3; five skills at 2; seven skills at 1."}
   :specialist
   {:name "Specialist"
    :description "Take one skill at 4; three skills at 3; three skills at 2; three skills at 1."}})

(def attributes
  {:strength
   {1 "You can easily crush a beer can (20kg: a Christmas tree, a stop sign)"
    2 "You are physically average (45kg: a toilet)"
    3 "You might be able to break open a wooden door (115kg: a large human, a refrigerator)"
    4 "You are a prime physical specimen, likely with very visible musculature (180kg: a full coffin, an empty dumpster)"
    5 "You are a true powerhouse and can likely break open a metal fire door, tear open a chain-link fence, or snap open a chained gate (250kg: a motorcycle, a piano)"}
   :dexterity
   {1 "You can run, but balance and dodging are a challenge"
    2 "You sprint is solid, and sometimes you appear graceful"
    3 "Your agility is impressive, and your coordination is as good as any trained amateur"
    4 "You could excel at acrobatics and move in a way few humans can"
    5 "Your movements are liquid and hypnotic - almost super-human"}
   :stamina
   {1 "Even lesser exertions make you winded"
    2 "You can take a beating, but consider suing for peace"
    3 "Several days of hard hiking with a backpack is no problem for you"
    4 "You could win a marathon or take copious amounts of pain, at least physically"
    5 "Even if you were a mortal, you'd never break a sweat"}
   :charisma
   {1 "You can speak clearly, though few people tend to listen"
    2 "Generally likeable despite your undead nature, you may even have friends"
    3 "People trust you implicitly, and you easily make friends"
    4 "You possess significant personal mgnetism and draw followers like flies"
    5 "You could lead a city in rebellion, if you so choose"}
   :manipulation
   {1 "As long as you stay honest, you can convince people to do what you want"
    2 "Your ability to deceive surpasses the will of the weak-willed and simple-minded"
    3 "You never have to pay full price for anything"
    4 "You could be a cult leader - or a politician"
    5 "You could convince the Prince to invest in desert property, or maybe even to call of the Blood Hunt on your head"}
   :composure
   {1 "The slightest insult or confrontation could drive you to frenzy"
    2 "You can subdue your predatory instincts in most non-hostile situations"
    3 "Others look to you for guidance when the blood spatter hits the fan"
    4 "You can effortlessly bluff at cards and can manage your Beast to some extent"
    5 "The Beast is your pet"}
   :intelligence
   {1 "You can read and write competently, though some terms confound you"
    2 "You are smart enough to realize your limitations"
    3 "You are enlightened, able to piece together clues without difficulty"
    4 "You're likely consulted by members of Clan Tremere for your wisdom"
    5 "Genius does not cover the depths and range of your intellect"}
   :wits
   {1 "You get the point eventually, but it takes explaining"
    2 "You can bet the odds in poker or apply the emergency brakes in time, usually"
    3 "You can analyze a situation and quickly work out the best escape route"
    4 "You are never caught on the back foot and always come up with a smart riposte"
    5 "You think and respond more quickly than most people can comprehend"}
   :resolve
   {1 "You have minimal attention for all but the most pressing things"
    2 "You can settle in for the long haul, as long as it's not too long"
    3 "Distracting you takes more effort than most other people want to spend"
    4 "You can brute-force your way to a deduction past any obstacles"
    5 "You can think in a gunfight or watch the door in a blood orgy and then clean up every shell casing or spilled droplet"}})
