
from HashTableClass import HashTable
from tkinter import *
from spellchecker import SpellChecker #Biblioteca que sugere as palavras

H=HashTable() # variável global

root = Tk() # definição global da interface a ser montada
root.title("Dicionário") # título da interface
root.geometry('200x150') # tamanho da interface

descricao = Label(root, text="Digite o texto e\n insira 'espaço'", foreground="black") # descrição a ser exibida
descricao.pack() # montagem da descrição dentro do frame

palavraValidada = StringVar() # declaração da variável string a ter a digitação observada 
e = Entry(root, textvariable=palavraValidada, foreground="black") # definição do widget de entrada de dados vinculado a variável definida
palavraValidada.trace("w", lambda name, index, mode, palavraValidada=palavraValidada: callback(e)) # declaração do listener da variável

padding = Label(root, text="") # definição de ajustes visuais do frame
padding.pack()

mensagem = Label(root, text="", foreground="red") # declaração do widget utilizado para exibição de mensagens de validação


def checkspell():
  
  pt = SpellChecker(language='pt') # inicializa o corretor da biblioteca
  candidatas = pt.candidates(palavraValidada.get()) # Pega sugestões da biblioteca
  for candidata in candidatas: # Loop na lista de sugestões
    if(valida(candidata)):

      mensagem.configure({"foreground":"black"})

      mensagem.configure({"text":"você quis dizer " + candidata + "?"})

      mensagem.configure({"foreground":"green"}) 
    
      adicionar.pack() # exibição do botão de adicionar nova palavra'''


def partial():
  # função utilizada para a criação do botão de Adicionar palavras ao dicionário
  
  pt = SpellChecker(language='pt') # inicializa o corretor da biblioteca
  candidatas = pt.candidates(palavraValidada.get()) # Pega sugestões da biblioteca
  for candidata in candidatas: # Loop na lista de sugestões
    if(valida(candidata)):
      mensagem.configure({"text":candidata + " foi adicionada"})
      e.configure({"foreground":"green"})


adicionar = Button(root, text="Adicionar", command = partial) # declaração do widget do botão


def valida(text):
  # função acionada pelo listener do widget de entrada de texto
  return H.get(text)


def callback(palavraValidada):
  # função executada pelo listener ao longo da digitação
  if(len(palavraValidada.get())):
    if(palavraValidada.get()[-1] == ' '):
      # se o último caracter digitado for um 'espaço'
      if(valida(palavraValidada.get().strip())):
        # efetua a validação, se for verdadeiro:
        palavraValidada.configure({"foreground":"green"}) # pinta a letra de verde
        adicionar.pack_forget() # remoção do botão de adicionar nova palavra
      else: 
        # se falhar a validação torna o texto vermelho
        palavraValidada.configure({"foreground":"red"})
        #mensagem.configure({"text":"Não encontrado"}) # exibição da mensagem de erro
        checkspell()
    else:
      # se o tamanho da string for 1, torna o texto preto e sem demais validações/mensagens de erro
      palavraValidada.configure({"foreground":"black"})
      mensagem.configure({"text":""})
      adicionar.pack_forget()
  
def main():
  # carregamento dos dados do arquivo para a tabela hash

  with open('pt.dic', encoding="utf8", errors='ignore') as dicionario:
    contents = dicionario.readlines()
  
  # for i in range(len(contents)):
  for i in range(5000):
    H.put(contents[i].strip().replace('\00', ''),i)
  
  e.pack() # exibição do frame
  mensagem.pack() # exibição da mensagem

  
  root.mainloop()  # início da interface

main()
