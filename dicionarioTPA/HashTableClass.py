
class HashTable:
  # definição da classe da tabela hash
  def __init__(self):
    self.size = 5000 # tamanho
    self.slots = [None] * self.size # array de indices
    self.data = [None] * self.size # array de dados

  
  def hashfunction(self,key,size):
    # a chave ode ser entendida como uma sequência de valores ordinais.
    # é somado o valor das lettras e com o resto da divisão é extraido um valor de espalhamento
    sum = 0
    for pos in range(len(key)):
       sum = sum + ord(key[pos])

    return sum%size
  
  def rehash(self,oldhash,size):
    # retorna o resto da divisão do hash antigo ao quadrado sobre o tamanho total da tabela
    return (oldhash**2)%size
    
  def put(self,key,data):
    # insere dados na tabela hash
    hashvalue = self.hashfunction(key,len(self.slots))

    initialPos = hashvalue
    # se não encontrar dados no índice do hash calculado
    if self.slots[hashvalue] == None:
      # insere os dados em seus respectivos arrays
      self.slots[hashvalue] = data
      self.data[hashvalue] = key
    else:
      # se já houver dados no índice hash calculado
      nextslot = self.rehash(key,hashvalue,len(self.slots)) # recalcula o valor hash desta chave
      # enquanto não encontrar um índice vazio, continua recalculando o hash
      while self.slots[nextslot] != None and \
                      self.slots[nextslot] != key:
        if nextslot != initialPos:
          nextslot = self.rehash(key,nextslot,len(self.slots))
        else:
          break
      # se encontrou índice vazio
      if self.slots[nextslot] == None:
        # insere os dados nos respectivos arrays
        self.slots[nextslot]=data
        self.data[nextslot]=key
      else: 
        return 'Lista cheia'
  
  def get(self,key):
    # recupera dados da tabela
    startslot = self.hashfunction(key,len(self.slots))
    found = False # inicializa flag de encontrado
    position = startslot # posição inicial é o hash calculado através da chave informada
  
    
    # enquanto não encontrar
    while self.slots[position] != None and not found:
          
      if self.data[position] == key:        
        found = True

    return found
  
  def __getitem__(self,key):
    return self.get(key)
  
  def __setitem__(self,key,data):
    self.put(key,data)