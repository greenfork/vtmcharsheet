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
   {:description "Take one skill at 3; eight skills at 2; ten skills at 1."}
   :balanced
   {:description "Take three skills at 3; five skills at 2; seven skills at 1."}
   :specialist
   {:description "Take one skill at 4; three skills at 3; three skills at 2; three skills at 1."}})